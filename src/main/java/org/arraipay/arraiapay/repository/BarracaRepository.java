package org.arraipay.arraiapay.repository;

import org.arraipay.arraiapay.domain.Barraca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BarracaRepository extends JpaRepository<Barraca, Long> {
    boolean existsByNomeIgnoreCase(String nome);
}
