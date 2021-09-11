package com.everis.desafioBanco.service;

import com.everis.desafioBanco.model.Cliente;
import com.everis.desafioBanco.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public void cadastrarCliente(Cliente cliente){

        cliente.setNome(cliente.getNome());
        cliente.setCpf(cliente.getCpf());
        cliente.setTelefone(cliente.getTelefone());
        cliente.setEndereco(cliente.getEndereco());

        clienteRepository.save(cliente);
    }

}
