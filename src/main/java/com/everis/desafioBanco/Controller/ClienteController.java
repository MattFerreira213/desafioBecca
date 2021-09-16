package com.everis.desafioBanco.Controller;

import com.everis.desafioBanco.Dto.ClienteDto;
import com.everis.desafioBanco.Model.Cliente;
import com.everis.desafioBanco.Service.ClienteService;
import com.everis.desafioBanco.Utils.Exceptions.ClienteExistenteException;
import com.everis.desafioBanco.Utils.Exceptions.CpfNaoEncontradoException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/cadastrar-cliente")
    public ResponseEntity<?> cadastrarClienet(@RequestBody @Valid ClienteDto clienteDto) {
        try{
            Cliente dadosCliente = new Cliente();
            BeanUtils.copyProperties(clienteDto, dadosCliente);
            clienteService.cadastrarCliente(dadosCliente);
            return ResponseEntity.status(HttpStatus.CREATED).body(dadosCliente);
        } catch (ClienteExistenteException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @GetMapping("/listar-cliente")
    public ResponseEntity<?> listarCLiente(@RequestParam(name = "cpf") String cpf){
        try {
            var cliente = clienteService.listarCliente(cpf);
            return ResponseEntity.ok(cliente);
        } catch (CpfNaoEncontradoException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PutMapping("/atualizar-cliente")
    public ResponseEntity<?> atualizarCliente(@RequestBody @Valid ClienteDto clienteDto, @RequestParam(name = "cpf") String cpf){
        try {
            var cliente = clienteService.listarCliente(cpf);
            BeanUtils.copyProperties(clienteDto, cliente);
            clienteService.atualizarCliente(cliente, cpf);
            return ResponseEntity.ok(cliente);
        } catch (CpfNaoEncontradoException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @DeleteMapping("/deletar-cliente")
    public ResponseEntity<?> deletarCliente(@RequestParam(name = "cpf") String cpf){
        try{
            clienteService.deletarCliente(cpf);
            return ResponseEntity.ok("Cliente deletado com sucesso.");
        } catch (CpfNaoEncontradoException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

}
