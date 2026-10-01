# Contrato inicial da API ArraiPay

Versão inicial para orientar a implementação dos controllers e a integração entre os grupos. Base URL: `/api/v1`. Formato: JSON UTF-8. Datas em ISO 8601; valores monetários em decimal com duas casas. IDs são inteiros.

O esqueleto inicial dos controllers está em `src/main/java/org/arraipay/arraiapay/api/controller`. Até a implementação dos serviços, as rotas respondem `501 Not Implemented` com erro `NOT_IMPLEMENTED`; isso evita indicar sucesso sem executar a operação.

## Convenções

- Operações protegidas usam `Authorization: Bearer <token>`. O usuário e seu perfil são obtidos do token; o cliente não escolhe `id_operador` ou `id_autorizador` no corpo.
- `POST /vendas` exige `Idempotency-Key`. O servidor persiste a chave como `chave_unica` e executa validação do cartão, preço, estoque, débito e gravação da venda numa única transação.
- A API não permite alterar saldo diretamente. Recargas, vendas, estornos e reembolsos são os únicos movimentos e mantêm os respectivos registros históricos.
- `PATCH` altera dados cadastrais ou estado explicitamente permitido. Para estornos, usa-se uma ação própria com justificativa; o registro original não é apagado.
- Listagens aceitam `page` (começa em 0), `size` e `sort`. Filtros temporais usam `inicio` e `fim` em ISO 8601.

## Autenticação e usuários

| Método e caminho | Ação | Perfis |
|---|---|---|
| `POST /auth/login` | Autentica com `login` e `senha`; retorna token e dados mínimos do usuário. | Público |
| `GET /auth/me` | Retorna o usuário e perfil da sessão atual. | Todos autenticados |
| `POST /usuarios` | Cadastra usuário, perfil e, para perfil `barraca`, a barraca vinculada. | Administrador |
| `GET /usuarios` | Lista usuários, com filtro opcional `ativo` e `perfil`. | Administrador |
| `GET /usuarios/{id}` | Consulta usuário. Nunca retorna hash da senha. | Administrador |
| `PATCH /usuarios/{id}` | Altera nome, perfil, barraca vinculada ou estado ativo. | Administrador |

Perfis válidos: `administrador`, `supervisor`, `caixa`, `barraca`. O endpoint de cadastro/edição recebe senha em texto apenas no transporte seguro; o servidor armazena somente o hash.

## Cartões

| Método e caminho | Ação | Perfis |
|---|---|---|
| `POST /cartoes` | Emite cartão. `nome_participante` é opcional; o servidor gera `codigo_qr` único e inicia saldo em `0.00`. | Administrador |
| `GET /cartoes/{id}` | Consulta dados do cartão, sem incluir dados sensíveis desnecessários. | Administrador, supervisor, caixa, barraca |
| `POST /cartoes/validacoes` | Valida o `codigo_qr`; retorna ID e estado ou erro E01, E02 ou E03. | Caixa, barraca |
| `GET /cartoes/{id}/saldo` | Consulta `saldo_atual` e estado. | Administrador, supervisor, caixa, barraca |
| `PATCH /cartoes/{id}/status` | Bloqueia, desbloqueia ou cancela cartão, enviando `status` e `justificativa` quando aplicável. | Administrador, supervisor; caixa pode bloquear |
| `POST /cartoes/{id}/segunda-via` | Cancela o cartão anterior, cria novo QR ativo, transfere o saldo e registra a relação entre cartões. | Administrador, supervisor, caixa |
| `GET /cartoes/{id}/extrato` | Lista movimentos cronológicos com saldo após cada movimento. | Administrador |

O QR contém somente o identificador aleatório. A validação nunca aceita cartão bloqueado ou cancelado para operações financeiras.

## Caixa: recargas, reembolsos e fechamento

| Método e caminho | Ação | Perfis |
|---|---|---|
| `POST /recargas` | Credita cartão ativo. Corpo: `id_cartao`, `valor`, `forma_pagamento` (`dinheiro`, `pix`, `cartao`). | Administrador, supervisor, caixa |
| `GET /recargas/{id}` | Consulta recarga e estado. | Administrador, supervisor, caixa |
| `GET /recargas` | Lista recargas por período, cartão ou estado. | Administrador, supervisor |
| `POST /recargas/{id}/solicitacoes-estorno` | Solicita estorno com `justificativa`; cria solicitação pendente. | Caixa |
| `POST /recargas/{id}/estorno` | Autoriza e efetiva estorno uma única vez; o autorizador vem da sessão autenticada. | Supervisor, administrador |
| `POST /reembolsos` | Devolve saldo disponível. Corpo: `id_cartao` e `valor`; não pode exceder o saldo. | Administrador, supervisor, caixa |
| `GET /reembolsos` | Lista reembolsos por período ou cartão. | Administrador, supervisor |
| `POST /fechamentos-caixa` | Fecha o caixa do operador autenticado com valores contados por forma de pagamento e `extrato_conta`. | Administrador, supervisor, caixa (próprio caixa) |
| `GET /fechamentos-caixa/{id}` | Consulta fechamento. | Administrador, supervisor, caixa (próprio) |
| `GET /fechamentos-caixa` | Lista fechamentos por operador e período. | Administrador, supervisor |

Os limites mínimo e máximo de recarga ainda dependem de decisão da organização. A API deve rejeitar valores fora dos limites com E05 após essa configuração.

## Barracas, produtos e estoque

| Método e caminho | Ação | Perfis |
|---|---|---|
| `POST /barracas` | Cadastra barraca. | Administrador |
| `GET /barracas` | Lista barracas; aceita filtro `ativa`. | Administrador, supervisor, barraca |
| `GET /barracas/{id}` | Consulta barraca. | Administrador, supervisor, barraca |
| `PATCH /barracas/{id}` | Edita dados ou ativa/desativa sem apagar histórico. | Administrador |
| `GET /barracas/{id}/cardapio` | Lista produtos ativos, com preço, categoria e disponibilidade. Barraca inativa retorna E08. | Administrador, supervisor, caixa, barraca |
| `POST /produtos` | Cadastra produto vinculado a uma barraca. | Administrador, supervisor |
| `GET /produtos/{id}` | Consulta produto. | Administrador, supervisor, barraca |
| `PATCH /produtos/{id}` | Edita nome, categoria, preço, estoque, estoque mínimo ou estado ativo. | Administrador, supervisor; barraca apenas na própria barraca |
| `POST /produtos/{id}/movimentos-estoque` | Registra entrada ou ajuste manual de estoque com quantidade e justificativa. Baixas da venda e devoluções de estorno são automáticas. | Administrador, supervisor; barraca apenas na própria barraca |

Categorias: `comida`, `bebida`, `doce`, `brincadeira`, `outro`. Produto sem controle de estoque usa `estoque: null`. Venda valida que o produto está ativo e pertence à barraca informada.

## Vendas e estornos

| Método e caminho | Ação | Perfis |
|---|---|---|
| `POST /vendas` | Registra venda e débito. Corpo: `id_cartao`, `id_barraca`, `itens` (`id_produto`, `quantidade`). Preço vem do servidor. | Administrador, barraca (própria barraca) |
| `GET /vendas/{id}` | Consulta venda e itens, incluindo preço unitário histórico. | Administrador, supervisor, barraca (própria barraca) |
| `GET /vendas` | Lista por período, cartão, barraca ou estado. | Administrador, supervisor; barraca (própria barraca) |
| `POST /vendas/{id}/solicitacoes-estorno` | Solicita estorno com `justificativa`; cria solicitação pendente. | Barraca (própria barraca) |
| `POST /vendas/{id}/estorno` | Autoriza e efetiva estorno uma única vez, devolvendo saldo e estoque; autorizador vem da sessão autenticada. | Supervisor, administrador |

Estados da venda: `concluida`, `estornada`. Repetir uma chave idempotente com o mesmo conteúdo retorna a venda já criada; repetir com conteúdo diferente retorna conflito. A venda reúne produtos de uma única barraca.

## Relatórios (somente leitura)

| Método e caminho | Ação |
|---|---|
| `GET /relatorios/dashboard?inicio=&fim=` | Totais recarregados, consumidos e saldo em aberto. |
| `GET /relatorios/vendas-por-barraca?inicio=&fim=` | Quantidade e valor por barraca. |
| `GET /relatorios/produtos-mais-vendidos?inicio=&fim=&limite=10` | Ranking de produtos por quantidade. |
| `GET /relatorios/movimento-por-hora?inicio=&fim=` | Totais agrupados por hora e horário de pico. |
| `GET /relatorios/conciliacao?inicio=&fim=` | Recargas − consumo − reembolsos e saldo em aberto, com diferença calculada. |
| `GET /relatorios/extrato-cartao/{id}?inicio=&fim=` | Extrato completo de movimentos e saldo após cada movimento. |
| `GET /relatorios/exportacao?tipo=&formato=csv` | Exporta relatório selecionado em CSV ou PDF. |

Todos os relatórios são restritos ao perfil `administrador`.

## Formato de erro

Erros retornam JSON uniforme, sem detalhes internos:

```json
{
  "codigo": "E04",
  "mensagem": "Saldo insuficiente.",
  "detalhes": { "saldo": 10.00, "faltam": 2.00 },
  "timestamp": "2026-10-01T12:00:00Z",
  "caminho": "/api/v1/vendas"
}
```

Mapeamento inicial: E01/E02/E03 cartão inválido ou indisponível; E04 saldo insuficiente; E05 limite de recarga; E06 produto indisponível; E07 estoque insuficiente; E08 barraca indisponível; E09 sem permissão; E10 estorno sem justificativa/autorização; E11 registro já estornado; E12 venda duplicada; E13 saldo insuficiente para estornar recarga; E14 falha transacional. Usar HTTP 400 para entrada inválida, 401 para sessão ausente/inválida, 403 para E09, 404 para recurso inexistente, 409 para conflitos de estado/idempotência, e 500 para E14.

## Exemplos de operações

### Criar venda

`POST /api/v1/vendas`

```http
Authorization: Bearer <token>
Idempotency-Key: 8a913c6f-b2f3-4d4a-94e0-560101357d80
Content-Type: application/json
```

```json
{
  "id_cartao": 42,
  "id_barraca": 3,
  "itens": [
    { "id_produto": 17, "quantidade": 2 }
  ]
}
```

Resposta `201 Created` contém venda, total, estado e itens com preço praticado. Uma repetição idempotente pode responder `200 OK` com a venda já registrada.

### Fazer recarga

`POST /api/v1/recargas`

```json
{
  "id_cartao": 42,
  "valor": 50.00,
  "forma_pagamento": "pix"
}
```

Resposta `201 Created` contém `id_recarga`, `id_cartao`, valor, forma, data/hora e estado. O operador vem da sessão autenticada.

## Decisões que precisam ser fechadas antes dos controllers

1. Mínimo e máximo de recarga.
2. Campos e estados da solicitação de estorno pendente e prazo para autorização.
3. Formato e duração dos tokens de autenticação.
4. Política de exportação PDF e limite dos relatórios.
5. Campos de auditoria obrigatórios para ajustes manuais de estoque.
