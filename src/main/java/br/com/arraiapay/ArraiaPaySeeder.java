package br.com.arraiapay;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class ArraiaPaySeeder {
    private static final String DATABASE_URL =
            "jdbc:mysql://localhost:3306/arraiapay?useSSL=false&serverTimezone=UTC";

    private ArraiaPaySeeder() {
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String user = askRequired(scanner, "Usuário do MySQL: ");
            String password = ask(scanner, "Senha do MySQL (pressione Enter se não houver): ");
            List<ClientData> clients = askClients(scanner);

            try (Connection connection = DriverManager.getConnection(DATABASE_URL, user, password)) {
                connection.setAutoCommit(false);
                try {
                    seed(connection, clients);
                    connection.commit();
                    System.out.printf("%d cliente(s) e cartão(ões) inseridos com sucesso.%n", clients.size());
                } catch (SQLException | RuntimeException exception) {
                    connection.rollback();
                    throw exception;
                }
            } catch (SQLException exception) {
                System.err.println("Não foi possível preencher o banco: " + exception.getMessage());
                System.exit(1);
            }
        }
    }

    private static List<ClientData> askClients(Scanner scanner) {
        int quantity = askPositiveInt(scanner, "Quantidade de clientes para inserir: ");
        List<ClientData> clients = new ArrayList<>(quantity);

        for (int index = 1; index <= quantity; index++) {
            System.out.printf("%nDados do cliente %d:%n", index);
            String name = askRequired(scanner, "Nome: ");
            BigDecimal balance = askMoney(scanner, "Saldo: ");
            String cardStatus = askRequired(scanner, "Status do cartão: ");
            String schoolRelation = askRequired(scanner, "Relação com a escola: ");
            clients.add(new ClientData(name, balance, cardStatus, schoolRelation));
        }
        return clients;
    }

    private static void seed(Connection connection, List<ClientData> clients) throws SQLException {
        boolean cardHasClientColumn = hasColumn(connection, "cartao", "id_cliente");
        String cardSql = cardHasClientColumn
                ? "INSERT INTO cartao (id_cliente) VALUES (NULL)"
                : "INSERT INTO cartao () VALUES ()";
        String clientSql = """
                INSERT INTO cliente
                    (nome, saldo, status_cartao, relacao_escola, id_cartao)
                VALUES (?, ?, ?, ?, ?)
                """;

        try (PreparedStatement cardStatement = connection.prepareStatement(
                     cardSql, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement clientStatement = connection.prepareStatement(
                     clientSql, Statement.RETURN_GENERATED_KEYS)) {

            for (ClientData client : clients) {
                int cardId = insertCard(cardStatement);

                clientStatement.setString(1, client.name());
                clientStatement.setBigDecimal(2, client.balance());
                clientStatement.setString(3, client.cardStatus());
                clientStatement.setString(4, client.schoolRelation());
                clientStatement.setInt(5, cardId);
                clientStatement.executeUpdate();

                if (cardHasClientColumn) {
                    /*
                     * A migration declara cartao.id_cliente como FK para
                     * cartao.id (autorreferente), então o próprio id é válido.
                     */
                    try (PreparedStatement updateCardStatement = connection.prepareStatement(
                            "UPDATE cartao SET id_cliente = ? WHERE id = ?")) {
                        updateCardStatement.setInt(1, cardId);
                        updateCardStatement.setInt(2, cardId);
                        updateCardStatement.executeUpdate();
                    }
                }
            }
        }
    }

    private static boolean hasColumn(Connection connection, String table, String column)
            throws SQLException {
        try (ResultSet columns = connection.getMetaData().getColumns(
                connection.getCatalog(), null, table, column)) {
            return columns.next();
        }
    }

    private static int insertCard(PreparedStatement statement) throws SQLException {
        statement.executeUpdate();
        try (ResultSet keys = statement.getGeneratedKeys()) {
            if (!keys.next()) {
                throw new SQLException("O banco não retornou o id do cartão inserido.");
            }
            return keys.getInt(1);
        }
    }

    private static String ask(Scanner scanner, String message) {
        System.out.print(message);
        return scanner.nextLine().trim();
    }

    private static String askRequired(Scanner scanner, String message) {
        while (true) {
            String value = ask(scanner, message);
            if (!value.isBlank()) {
                return value;
            }
            System.out.println("Este campo é obrigatório.");
        }
    }

    private static int askPositiveInt(Scanner scanner, String message) {
        while (true) {
            try {
                int value = Integer.parseInt(ask(scanner, message));
                if (value > 0) {
                    return value;
                }
            } catch (NumberFormatException ignored) {
                // A mensagem abaixo orienta o usuário a tentar novamente.
            }
            System.out.println("Informe um número inteiro maior que zero.");
        }
    }

    private static BigDecimal askMoney(Scanner scanner, String message) {
        while (true) {
            try {
                String value = ask(scanner, message).replace(',', '.');
                BigDecimal balance = new BigDecimal(value);
                if (balance.scale() <= 2 && balance.signum() >= 0) {
                    return balance;
                }
            } catch (NumberFormatException ignored) {
                // A mensagem abaixo orienta o usuário a tentar novamente.
            }
            System.out.println("Informe um saldo válido, sem valor negativo e com no máximo duas casas decimais.");
        }
    }

    private record ClientData(
            String name,
            BigDecimal balance,
            String cardStatus,
            String schoolRelation) {
    }
}
