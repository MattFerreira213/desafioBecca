package com.everis.desafioBanco.Controller;

import com.everis.desafioBanco.Dto.ContaDto;
import com.everis.desafioBanco.Model.Conta;
import com.everis.desafioBanco.Service.ContaService;
import com.everis.desafioBanco.Utils.Exceptions.ContaExistenteException;
import com.everis.desafioBanco.Utils.Exceptions.CpfNaoEncontradoException;
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
    public ResponseEntity<?> criarConta(@RequestBody @Valid ContaDto contaDto){
        Conta dadosConta = new Conta();
        BeanUtils.copyProperties(contaDto, dadosConta);
        contaService.cadastrarConta(dadosConta);
        return ResponseEntity.status(HttpStatus.CREATED).body(dadosConta);
    }

    @GetMapping("/listar-conta")
    public ResponseEntity<?> listarConta(@RequestParam(name = "cpf") String cpf){
        var contas = contaService.listarConta(cpf);
        return ResponseEntity.ok(contas);
    }

    @DeleteMapping("/deletar-conta")
    public ResponseEntity<?> deletarConta(@RequestParam(name = "cpf") String cpf){
        contaService.deletarConta(cpf);
        return ResponseEntity.ok().build();
    }
}
