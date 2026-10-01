package org.arraipay.arraiapay.repository;

import org.arraipay.arraiapay.domain.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VendaRepository extends JpaRepository<Venda, Long> {
    Optional<Venda> findByChaveUnica(String chaveUnica);
}
