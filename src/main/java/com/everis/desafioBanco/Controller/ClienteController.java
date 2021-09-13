package com.everis.desafioBanco.Controller;

import com.everis.desafioBanco.Dto.ClienteDto;
import com.everis.desafioBanco.Model.Cliente;
import com.everis.desafioBanco.Service.ClienteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@RestController
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/cadastrar-cliente")
    public ResponseEntity<Cliente> cadastrarClienet(@RequestBody @Valid ClienteDto clienteDto){
        Cliente dadosCliente = new Cliente();
        BeanUtils.copyProperties(clienteDto, dadosCliente);
        clienteService.cadastrarCliente(dadosCliente);
        return new ResponseEntity<>(dadosCliente, HttpStatus.CREATED);
    }

    @GetMapping("/listar-cliente")
    public ResponseEntity<Cliente> listarCLiente(@RequestParam(name = "cpf") String cpf){
        var cliente = clienteService.listarCliente(cpf);
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @PutMapping("/atualizar-cliente")
    public ResponseEntity<Cliente> atualizarCliente(@RequestBody @Valid ClienteDto clienteDto, @RequestParam(name = "cpf") String cpf){
        var cliente = clienteService.listarCliente(cpf);
        BeanUtils.copyProperties(clienteDto, cliente);
        clienteService.atualizarCliente(cliente, cpf);
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @DeleteMapping("/deletar-cliente")
    public ResponseEntity<?> deletarCliente(@RequestParam(name = "cpf") String cpf){
        clienteService.deletarCliente(cpf);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
