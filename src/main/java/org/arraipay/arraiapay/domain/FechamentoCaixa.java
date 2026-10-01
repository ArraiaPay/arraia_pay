package org.arraipay.arraiapay.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "fechamento_caixa")
public class FechamentoCaixa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_fechamento")
    private Long idFechamento;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_operador", nullable = false)
    private Usuario operador;

    @Column(name = "data_hora", nullable = false, updatable = false)
    private LocalDateTime dataHora;

    @Column(name = "total_dinheiro", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalDinheiro = BigDecimal.ZERO;

    @Column(name = "total_pix", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalPix = BigDecimal.ZERO;

    @Column(name = "total_cartao", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalCartao = BigDecimal.ZERO;

    @Column(name = "extrato_conta", nullable = false, precision = 10, scale = 2)
    private BigDecimal extratoConta = BigDecimal.ZERO;

    @Column(name = "diferenca_cartao", precision = 10, scale = 2)
    private BigDecimal diferencaCartao;

    protected FechamentoCaixa() { }

    public FechamentoCaixa(Usuario operador, BigDecimal totalDinheiro, BigDecimal totalPix,
                           BigDecimal totalCartao, BigDecimal extratoConta) {
        this.operador = operador;
        this.totalDinheiro = totalDinheiro;
        this.totalPix = totalPix;
        this.totalCartao = totalCartao;
        this.extratoConta = extratoConta;
    }

    @PrePersist
    void definirDataHora() {
        if (dataHora == null) dataHora = LocalDateTime.now();
    }

    public Long getIdFechamento() { return idFechamento; }
    public Usuario getOperador() { return operador; }
    public LocalDateTime getDataHora() { return dataHora; }
    public BigDecimal getTotalDinheiro() { return totalDinheiro; }
    public BigDecimal getTotalPix() { return totalPix; }
    public BigDecimal getTotalCartao() { return totalCartao; }
    public BigDecimal getExtratoConta() { return extratoConta; }
    public BigDecimal getDiferencaCartao() { return diferencaCartao; }
    public void setDiferencaCartao(BigDecimal diferencaCartao) { this.diferencaCartao = diferencaCartao; }
}
