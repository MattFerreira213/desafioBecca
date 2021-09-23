package com.everis.desafioBanco.Repository;

import com.everis.desafioBanco.Model.OperacaoBancaria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OperacaoBancariaRepository extends JpaRepository<OperacaoBancaria, Long> {

    List<OperacaoBancaria> findExtrartoByNumeroDaConta(Long numeroDaConta);
}
