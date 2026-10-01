package org.arraipay.arraiapay.api.dto;

import java.math.BigDecimal;
import java.util.List;

/** DTOs de entrada usados pelas rotas da API. */
public final class Requests {
    private Requests() { }

    public record LoginRequest(String login, String senha) { }
    public record UsuarioRequest(String nome, String login, String senha, String perfil, Long idBarraca) { }
    public record UsuarioUpdateRequest(String nome, String perfil, Long idBarraca, Boolean ativo) { }
    public record CartaoRequest(String nomeParticipante) { }
    public record ValidacaoCartaoRequest(String codigoQr) { }
    public record StatusCartaoRequest(String status, String justificativa) { }
    public record RecargaRequest(Long idCartao, BigDecimal valor, String formaPagamento) { }
    public record EstornoRequest(String justificativa) { }
    public record ReembolsoRequest(Long idCartao, BigDecimal valor) { }
    public record FechamentoCaixaRequest(BigDecimal totalDinheiro, BigDecimal totalPix,
                                         BigDecimal totalCartao, BigDecimal extratoConta) { }
    public record BarracaRequest(String nome, String responsavel, Boolean ativa) { }
    public record ProdutoRequest(Long idBarraca, String nome, String categoria, BigDecimal preco,
                                 Integer estoque, Integer estoqueMinimo, Boolean ativo) { }
    public record ProdutoUpdateRequest(String nome, String categoria, BigDecimal preco,
                                       Integer estoque, Integer estoqueMinimo, Boolean ativo) { }
    public record MovimentoEstoqueRequest(String tipo, Integer quantidade, String justificativa) { }
    public record ItemVendaRequest(Long idProduto, Integer quantidade) { }
    public record VendaRequest(Long idCartao, Long idBarraca, List<ItemVendaRequest> itens) { }
}
