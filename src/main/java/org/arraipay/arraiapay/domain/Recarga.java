package org.arraipay.arraiapay.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Convert;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import org.arraipay.arraiapay.domain.enums.FormaPagamento;
import org.arraipay.arraiapay.domain.enums.FormaPagamentoConverter;
import org.arraipay.arraiapay.domain.enums.StatusRecarga;
import org.arraipay.arraiapay.domain.enums.StatusRecargaConverter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "recarga")
public class Recarga {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_recarga")
    private Long idRecarga;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_cartao", nullable = false)
    private Cartao cartao;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valor;

    @Convert(converter = FormaPagamentoConverter.class)
    @Column(name = "forma_pagamento", nullable = false, length = 20)
    private FormaPagamento formaPagamento;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_operador", nullable = false)
    private Usuario operador;

    @Column(name = "data_hora", nullable = false, updatable = false)
    private LocalDateTime dataHora;

    @Convert(converter = StatusRecargaConverter.class)
    @Column(nullable = false, length = 20)
    private StatusRecarga status = StatusRecarga.EFETIVADA;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_autorizador")
    private Usuario autorizador;

    @Column(name = "justificativa_estorno", length = 500)
    private String justificativaEstorno;

    protected Recarga() { }

    public Recarga(Cartao cartao, BigDecimal valor, FormaPagamento formaPagamento, Usuario operador) {
        this.cartao = cartao;
        this.valor = valor;
        this.formaPagamento = formaPagamento;
        this.operador = operador;
    }

    @PrePersist
    void definirDataHora() {
        if (dataHora == null) dataHora = LocalDateTime.now();
    }

    public Long getIdRecarga() { return idRecarga; }
    public Cartao getCartao() { return cartao; }
    public BigDecimal getValor() { return valor; }
    public FormaPagamento getFormaPagamento() { return formaPagamento; }
    public Usuario getOperador() { return operador; }
    public LocalDateTime getDataHora() { return dataHora; }
    public StatusRecarga getStatus() { return status; }
    public void setStatus(StatusRecarga status) { this.status = status; }
    public Usuario getAutorizador() { return autorizador; }
    public void setAutorizador(Usuario autorizador) { this.autorizador = autorizador; }
    public String getJustificativaEstorno() { return justificativaEstorno; }
    public void setJustificativaEstorno(String justificativaEstorno) { this.justificativaEstorno = justificativaEstorno; }
}
