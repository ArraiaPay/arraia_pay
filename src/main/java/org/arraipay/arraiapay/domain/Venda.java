package org.arraipay.arraiapay.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Convert;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import org.arraipay.arraiapay.domain.enums.StatusVenda;
import org.arraipay.arraiapay.domain.enums.StatusVendaConverter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "venda")
public class Venda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venda")
    private Long idVenda;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_cartao", nullable = false)
    private Cartao cartao;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_barraca", nullable = false)
    private Barraca barraca;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_operador", nullable = false)
    private Usuario operador;

    @Column(name = "valor_total", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorTotal;

    @Column(name = "data_hora", nullable = false, updatable = false)
    private LocalDateTime dataHora;

    @Convert(converter = StatusVendaConverter.class)
    @Column(nullable = false, length = 20)
    private StatusVenda status = StatusVenda.CONCLUIDA;

    @Column(name = "chave_unica", nullable = false, unique = true, length = 100)
    private String chaveUnica;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_autorizador")
    private Usuario autorizador;

    @Column(name = "justificativa_estorno", length = 500)
    private String justificativaEstorno;

    @OneToMany(mappedBy = "venda", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<ItemVenda> itens = new ArrayList<>();

    protected Venda() { }

    public Venda(Cartao cartao, Barraca barraca, Usuario operador, String chaveUnica) {
        this.cartao = cartao;
        this.barraca = barraca;
        this.operador = operador;
        this.chaveUnica = chaveUnica;
        this.valorTotal = BigDecimal.ZERO;
    }

    @PrePersist
    void definirDataHora() {
        if (dataHora == null) dataHora = LocalDateTime.now();
    }

    public void adicionarItem(ItemVenda item) {
        itens.add(item);
        item.setVenda(this);
        valorTotal = valorTotal.add(item.getSubtotal());
    }

    public Long getIdVenda() { return idVenda; }
    public Cartao getCartao() { return cartao; }
    public Barraca getBarraca() { return barraca; }
    public Usuario getOperador() { return operador; }
    public BigDecimal getValorTotal() { return valorTotal; }
    public LocalDateTime getDataHora() { return dataHora; }
    public StatusVenda getStatus() { return status; }
    public void setStatus(StatusVenda status) { this.status = status; }
    public String getChaveUnica() { return chaveUnica; }
    public Usuario getAutorizador() { return autorizador; }
    public void setAutorizador(Usuario autorizador) { this.autorizador = autorizador; }
    public String getJustificativaEstorno() { return justificativaEstorno; }
    public void setJustificativaEstorno(String justificativaEstorno) { this.justificativaEstorno = justificativaEstorno; }
    public List<ItemVenda> getItens() { return List.copyOf(itens); }
}
