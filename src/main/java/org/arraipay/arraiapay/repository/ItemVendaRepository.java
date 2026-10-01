package org.arraipay.arraiapay.repository;

import org.arraipay.arraiapay.domain.ItemVenda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemVendaRepository extends JpaRepository<ItemVenda, Long> {
    List<ItemVenda> findByVenda_IdVenda(Long idVenda);
}
