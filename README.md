# arraia_pay
(Protótipo) Sistema de pagamento de festas ArraiaPay

O contrato inicial da API REST está em [Diagrams_Requisites/api-endpoints.md](Diagrams_Requisites/api-endpoints.md).

## Estrutura inicial

- `domain`: entidades JPA e enums do domínio.
- `repository`: interfaces Spring Data para persistência.
- `api/controller`: rotas REST organizadas por recurso.
- `api/dto`: contratos de entrada e erro da API.
- `api/exception`: tratamento padronizado de endpoints ainda não implementados.

As regras de negócio e os serviços serão conectados aos controllers nas próximas etapas.
