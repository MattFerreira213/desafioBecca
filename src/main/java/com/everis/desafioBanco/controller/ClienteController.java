package com.everis.desafioBanco.controller;

import com.everis.desafioBanco.dto.ClienteDto;
import com.everis.desafioBanco.model.Cliente;
import com.everis.desafioBanco.service.ClienteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ClienteControler {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/cadastrar-cliente")
    public ResponseEntity<Cliente> cadastrarClienet(@RequestBody @Valid ClienteDto clienteDto){
        Cliente dadosCliente = new Cliente();
        BeanUtils.copyProperties(clienteDto, dadosCliente);
        clienteService.cadastrarCliente(dadosCliente);
        return new ResponseEntity<>(dadosCliente, HttpStatus.CREATED);
    }

}
