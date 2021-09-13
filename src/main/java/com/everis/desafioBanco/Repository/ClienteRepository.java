package com.everis.desafioBanco.Repository;

import com.everis.desafioBanco.Model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Cliente findClienteByCpf(String cpf);

}
