package com.everis.desafioBanco.repository;

import com.everis.desafioBanco.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Cliente findClienteByCpf(String cpf);

}
