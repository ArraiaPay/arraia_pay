package org.arraipay.arraiapay.Model_Barraca_Produto;

import lombok.Getter;
import lombok.Setter;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Produto {
    @Setter
    @Getter

    long id_produto;

    String nome;

    double preco;

    String categoria;

    Integer estoque;

    Integer Estoque_min;

    boolean ativo;

    @ManyToOne
}
