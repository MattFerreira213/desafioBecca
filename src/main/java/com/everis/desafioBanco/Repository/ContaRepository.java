package com.everis.desafioBanco.Repository;

import com.everis.desafioBanco.Model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContaRepository extends JpaRepository<Conta, Long> {

    List<Conta> findContaByCpf(String cpf);

    Conta findContaByNumeroDaConta(Long numeroDaConta);
}
