package com.everis.desafioBanco.Repository;

import com.everis.desafioBanco.Model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepository extends JpaRepository<Conta, Long> {

    Conta findContaByCpf(String cpf);

    Conta findContaByNumeroDaConta(Long numeroDaConta);
}
