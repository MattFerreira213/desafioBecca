package com.everis.desafioBanco.Repository;

import com.everis.desafioBanco.Model.OperacaoBancaria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperacaoBancariaRepository extends JpaRepository<OperacaoBancaria, Long> {
}
