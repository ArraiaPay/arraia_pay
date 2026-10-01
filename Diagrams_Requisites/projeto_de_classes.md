# Projeto de classes — ArraiPay

Modelo baseado na **Especificação Integrada do ArraiPay, versão 2.0**. As classes persistentes ficam em `org.arraipay.arraiapay.domain`; os estados e categorias ficam em `domain.enums`.

## Classes de domínio

| Classe | Responsabilidade e principais dados |
|---|---|
| `Usuario` | Operadores do sistema: nome, login único, hash da senha, perfil, barraca opcional e estado ativo. Os perfis são administrador, supervisor, caixa e barraca. |
| `Cartao` | Identificador QR único, nome opcional do participante, estado, saldo, data de criação e cartão anterior (segunda via). O QR é gerado com UUID e não contém saldo nem dados pessoais. A versão otimista permite detectar atualizações concorrentes do saldo. |
| `Barraca` | Nome único, responsável e estado ativo. |
| `Produto` | Barraca, nome, categoria, preço atual, estoque opcional, estoque mínimo e estado ativo. Estoque nulo significa que o controle não é usado. |
| `Recarga` | Cartão, valor, forma de pagamento, operador, data/hora, estado, autorizador e justificativa de estorno. |
| `Reembolso` | Cartão, valor devolvido, operador e data/hora. |
| `FechamentoCaixa` | Operador, data/hora, totais por forma de pagamento, valor extraído e diferença de conferência. |
| `Venda` | Cartão, barraca, operador, total, data/hora, estado, chave única contra duplicidade e dados de autorização/justificativa do estorno. Contém os itens da venda. |
| `ItemVenda` | Venda, produto, quantidade, preço unitário praticado e subtotal. O preço é copiado para preservar o histórico quando o preço do produto mudar. |

## Relacionamentos

- Uma `Barraca` pode ter vários `Usuario` do perfil `BARRACA` e vários `Produto`.
- Um `Cartao` tem várias `Recarga`, `Reembolso` e `Venda`; uma segunda via aponta para o cartão substituído.
- Uma `Venda` pertence a um `Cartao`, uma `Barraca` e um `Usuario`, e possui um ou mais `ItemVenda`.
- Cada `ItemVenda` aponta para um `Produto`; os itens preservam o preço aplicado no momento da compra.
- `Recarga`, `Reembolso`, `FechamentoCaixa` e `Venda` registram o `Usuario` responsável. Estornos também apontam para o usuário autorizador.

## Tipos controlados

- `PerfilUsuario`: `administrador`, `supervisor`, `caixa`, `barraca`.
- `StatusCartao`: `ativo`, `bloqueado`, `cancelado`.
- `FormaPagamento`: `dinheiro`, `pix`, `cartao`.
- `StatusRecarga`: `efetivada`, `estornada`.
- `StatusVenda`: `concluida`, `estornada`.
- `CategoriaProduto`: `comida`, `bebida`, `doce`, `brincadeira`, `outro`.

Os enums Java são persistidos em minúsculas, conforme as convenções do documento integrado. Valores monetários usam `BigDecimal` com precisão de duas casas decimais; datas operacionais são definidas pelo sistema.

## Correspondência com a lista inicial

Na especificação integrada, `Operador` não é uma entidade separada: os operadores são `Usuario` com um perfil. Da mesma forma, “Caixa” representa as operações e o registro `FechamentoCaixa`, em vez de uma entidade `Caixa` isolada. Essa escolha evita duplicar pessoas e segue as FKs e os campos comuns definidos para os cinco grupos.

## Regras que precisam ser aplicadas na camada de serviço

As entidades representam os dados e relacionamentos. A camada de serviço deve executar crédito, débito, estorno, reembolso e baixa/devolução de estoque dentro de transações; validar status/permissões/limites; impedir saldo negativo; exigir autorização e justificativa de estorno; e tratar a chave única da venda como idempotência. A versão de `Cartao` dá suporte à detecção de concorrência, mas as operações de saldo ainda precisam ser transacionais.
