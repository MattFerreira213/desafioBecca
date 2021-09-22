package com.everis.desafioBanco.Controller;

import com.everis.desafioBanco.Dto.ContaDto;
import com.everis.desafioBanco.Model.Conta;
import com.everis.desafioBanco.Service.ContaService;
import com.everis.desafioBanco.Exceptions.ContaExistenteException;
import com.everis.desafioBanco.Exceptions.CpfNaoEncontradoException;
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
        try {
            Conta dadosConta = new Conta();
            BeanUtils.copyProperties(contaDto, dadosConta);
            contaService.cadastrarConta(dadosConta);
            return ResponseEntity.status(HttpStatus.CREATED).body(dadosConta);
        } catch (ContaExistenteException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @GetMapping("/listar-conta")
    public ResponseEntity<?> listarConta(@RequestParam(name = "cpf") String cpf){
        try {
            var contas = contaService.listarConta(cpf);
            return ResponseEntity.ok(contas);
        } catch (CpfNaoEncontradoException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @DeleteMapping("/deletar-conta")
    public ResponseEntity<?> deletarConta(@RequestParam(name = "cpf") String cpf){
        try{
            contaService.deletarConta(cpf);
            return ResponseEntity.ok().build();
        }catch (CpfNaoEncontradoException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }
}
