# arraia_pay
(Protótipo) Sistema de pagamento de festas ArraiaPay

## Preenchendo o banco com Java

O projeto inclui um seeder JDBC interativo que se conecta ao MySQL em
`localhost:3306/arraiapay` e pergunta ao usuário os dados de conexão e de cada
cliente. É necessário ter Java 17+, Maven e o MySQL disponíveis.

1. Crie o banco e as tabelas executando `SQL_Queries/migration.sql`.
2. Execute:

```powershell
mvn compile exec:java
```

O programa perguntará o usuário e a senha do MySQL, a quantidade de clientes e
os valores de `nome`, `saldo`, `status_cartao` e `relacao_escola`. Os campos
`id` são gerados automaticamente pelo banco; `id_cartao` é preenchido com o
cartão criado para cada cliente.

O seeder verifica automaticamente se `cartao.id_cliente` existe no banco. Isso
permite executar o programa também em uma versão da tabela que tenha somente a
coluna `id`; nesse caso, o cartão é inserido com seu ID e o cliente recebe esse
valor em `id_cartao`.

> Observação: `SQL_Queries/migration.sql` declara `cartao.id_cliente` como uma
> chave estrangeira para a própria tabela `cartao`. Por isso o programa insere
> o cartão com `NULL` e depois preenche essa coluna com o próprio `id`, que é um
> valor válido nessa definição. Se a intenção era referenciar `cliente.id`,
> essa constraint deve ser corrigida na migration antes de alterar o seeder.
