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
@Table(name = "reembolso")
public class Reembolso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reembolso")
    private Long idReembolso;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_cartao", nullable = false)
    private Cartao cartao;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valor;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_operador", nullable = false)
    private Usuario operador;

    @Column(name = "data_hora", nullable = false, updatable = false)
    private LocalDateTime dataHora;

    protected Reembolso() { }

    public Reembolso(Cartao cartao, BigDecimal valor, Usuario operador) {
        this.cartao = cartao;
        this.valor = valor;
        this.operador = operador;
    }

    @PrePersist
    void definirDataHora() {
        if (dataHora == null) dataHora = LocalDateTime.now();
    }

    public Long getIdReembolso() { return idReembolso; }
    public Cartao getCartao() { return cartao; }
    public BigDecimal getValor() { return valor; }
    public Usuario getOperador() { return operador; }
    public LocalDateTime getDataHora() { return dataHora; }
}
