package org.arraipay.arraiapay.repository;

import org.arraipay.arraiapay.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    List<Produto> findByBarraca_IdBarracaAndAtivoTrue(Long idBarraca);
}
