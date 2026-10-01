package org.arraipay.arraiapay.repository;

import org.arraipay.arraiapay.domain.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartaoRepository extends JpaRepository<Cartao, Long> {
    Optional<Cartao> findByCodigoQr(String codigoQr);
}
