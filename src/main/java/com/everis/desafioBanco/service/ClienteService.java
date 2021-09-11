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

        Cliente dadosCliente = new Cliente();

        dadosCliente.setNome(cliente.getNome());
        dadosCliente.setCpf(cliente.getCpf());
        dadosCliente.setTelefone(cliente.getTelefone());
        dadosCliente.setEndereco(cliente.getEndereco());

        clienteRepository.save(dadosCliente);
    }

    public Cliente listarCliente(String cpf){
        var cliente = clienteRepository.findClienteByCpf(cpf);
        cliente.getNome();
        cliente.getCpf();
        cliente.getTelefone();
        cliente.getEndereco();

        return cliente;
    }

    public Cliente atualizarCliente(Cliente novosDadosCliente, String cpf){
        var cliente = clienteRepository.findClienteByCpf(cpf);
        cliente.setNome(novosDadosCliente.getNome());
        cliente.setTelefone(novosDadosCliente.getTelefone());
        cliente.setEndereco(novosDadosCliente.getEndereco());

        return cliente;
    }

    public void deletarCliente(String cpf){
        var cliente = clienteRepository.findClienteByCpf(cpf);
        clienteRepository.delete(cliente);
    }

}
