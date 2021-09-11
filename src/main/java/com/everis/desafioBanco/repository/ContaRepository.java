package com.everis.desafioBanco.repository;

import com.everis.desafioBanco.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepository extends JpaRepository<Conta, Long> {

    Conta findContaByCpf(String cpf);
}
