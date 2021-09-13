package com.everis.desafioBanco.Service;

import com.everis.desafioBanco.Model.Cliente;
import com.everis.desafioBanco.Repository.ClienteRepository;
import com.everis.desafioBanco.Utils.Exception.CpfNaoEncontradoException;
import org.springframework.beans.BeanUtils;
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
        try {
            var cliente = clienteRepository.findClienteByCpf(cpf);
            cliente.getNome();
            cliente.getCpf();
            cliente.getTelefone();
            cliente.getEndereco();
            return cliente;
        } catch (Exception e){
            throw new CpfNaoEncontradoException(
                    String.format("Cliente de CPF %s não encontrado", cpf)
            );
        }
    }

    public Cliente atualizarCliente(Cliente dadosCliente, String cpf){
        var dadosAtualcliente = clienteRepository.findClienteByCpf(cpf);
        BeanUtils.copyProperties(dadosCliente, dadosAtualcliente);
        return clienteRepository.save(dadosAtualcliente);
    }

    public void deletarCliente(String cpf){

        try {
            var cliente = clienteRepository.findClienteByCpf(cpf);
            clienteRepository.delete(cliente);
        } catch (Exception e){
            throw new CpfNaoEncontradoException(
                    String.format("CPF %s não encontrado", cpf)
            );
        }
    }

}
