package com.everis.desafioBanco.controller;

import com.everis.desafioBanco.dto.ContaDto;
import com.everis.desafioBanco.model.Cliente;
import com.everis.desafioBanco.model.Conta;
import com.everis.desafioBanco.service.ContaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ContaController {

    @Autowired
    private ContaService contaService;

    @PostMapping("/criar-conta")
    public ResponseEntity<Conta> criarConta(@RequestBody @Valid ContaDto contaDto){
        Conta dadosConta = new Conta();
        BeanUtils.copyProperties(contaDto, dadosConta);
        contaService.cadastrarConta(dadosConta);
        return new ResponseEntity<>(dadosConta, HttpStatus.CREATED);
    }

    @GetMapping("/listar-conta")
    public ResponseEntity<Conta> listarConta(@RequestParam(name = "cpf") String cpf){
        var conta = contaService.listarConta(cpf);
        return new ResponseEntity<>(conta, HttpStatus.OK);
    }

    @DeleteMapping("/deletar-conta")
    public ResponseEntity<?> deletarConta(@RequestParam(name = "cpf") String cpf){
        contaService.deletarConta(cpf);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
