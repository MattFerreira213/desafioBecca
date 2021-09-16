package com.everis.desafioBanco.Service;

import com.everis.desafioBanco.Model.Cliente;
import com.everis.desafioBanco.Repository.ClienteRepository;
import com.everis.desafioBanco.Utils.Exceptions.ClienteExistenteException;
import com.everis.desafioBanco.Utils.Exceptions.CpfNaoEncontradoException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    private static Optional<Cliente> verificarExistenciaDoCliente;

    public void cadastrarCliente(Cliente cliente) {

        Cliente dadosCliente = new Cliente();
        dadosCliente.setNome(cliente.getNome());
        dadosCliente.setCpf(cliente.getCpf());
        dadosCliente.setTelefone(cliente.getTelefone());
        dadosCliente.setEndereco(cliente.getEndereco());

        verificarExistenciaDoCliente = Optional.ofNullable(clienteRepository.findClienteByCpf(dadosCliente.getCpf()));

        if (!verificarExistenciaDoCliente.isPresent()) {
            clienteRepository.save(dadosCliente);
        } else {
            throw new ClienteExistenteException("Esse cpf já está cadastrado");
        }
    }

    public Cliente listarCliente(String cpf) {
        var cliente = clienteRepository.findClienteByCpf(cpf);

        verificarExistenciaDoCliente = Optional.ofNullable(cliente);

        if (verificarExistenciaDoCliente.isPresent()) {
            return cliente;
        } else {
            throw new CpfNaoEncontradoException(
                    String.format("Cliente de cpf %s não encontrado ou não existe.", cpf));
        }
    }

    public Cliente atualizarCliente(Cliente dadosCliente, String cpf) {
        var dadosAtualcliente = clienteRepository.findClienteByCpf(cpf);
        BeanUtils.copyProperties(dadosCliente, dadosAtualcliente);

        verificarExistenciaDoCliente = Optional.ofNullable(dadosAtualcliente);

        if (verificarExistenciaDoCliente.isPresent()) {
            return clienteRepository.save(dadosAtualcliente);
        } else {
            throw new CpfNaoEncontradoException(
                    String.format("Cliente de cpf %s não encontrado ou não existe.", cpf));
        }
    }

    public void deletarCliente(String cpf) {
        var cliente = clienteRepository.findClienteByCpf(cpf);

        verificarExistenciaDoCliente = Optional.ofNullable(cliente);

        if (verificarExistenciaDoCliente.isPresent()) {
            clienteRepository.delete(cliente);
        } else {
            throw new CpfNaoEncontradoException(
                    String.format("Cliente de cpf %s não encontrado ou não existe.", cpf));
        }
    }
}
