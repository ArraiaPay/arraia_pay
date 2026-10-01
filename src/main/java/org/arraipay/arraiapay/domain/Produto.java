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
import jakarta.persistence.Table;
import org.arraipay.arraiapay.domain.enums.CategoriaProduto;
import org.arraipay.arraiapay.domain.enums.CategoriaProdutoConverter;

import java.math.BigDecimal;

@Entity
@Table(name = "produto")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produto")
    private Long idProduto;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_barraca", nullable = false)
    private Barraca barraca;

    @Column(nullable = false, length = 100)
    private String nome;

    @Convert(converter = CategoriaProdutoConverter.class)
    @Column(nullable = false, length = 20)
    private CategoriaProduto categoria;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal preco;

    // Null representa produto sem controle de estoque.
    private Integer estoque;

    @Column(name = "estoque_minimo")
    private Integer estoqueMinimo;

    @Column(nullable = false)
    private boolean ativo = true;

    protected Produto() { }

    public Produto(Barraca barraca, String nome, CategoriaProduto categoria, BigDecimal preco) {
        this.barraca = barraca;
        this.nome = nome;
        this.categoria = categoria;
        this.preco = preco;
    }

    public Long getIdProduto() { return idProduto; }
    public Barraca getBarraca() { return barraca; }
    public void setBarraca(Barraca barraca) { this.barraca = barraca; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public CategoriaProduto getCategoria() { return categoria; }
    public void setCategoria(CategoriaProduto categoria) { this.categoria = categoria; }
    public BigDecimal getPreco() { return preco; }
    public void setPreco(BigDecimal preco) { this.preco = preco; }
    public Integer getEstoque() { return estoque; }
    public void setEstoque(Integer estoque) { this.estoque = estoque; }
    public Integer getEstoqueMinimo() { return estoqueMinimo; }
    public void setEstoqueMinimo(Integer estoqueMinimo) { this.estoqueMinimo = estoqueMinimo; }
    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }
}
