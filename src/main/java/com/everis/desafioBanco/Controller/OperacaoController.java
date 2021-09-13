package com.everis.desafioBanco.Controller;

import com.everis.desafioBanco.Dto.ClienteDto;
import com.everis.desafioBanco.Dto.ContaDto;
import com.everis.desafioBanco.Dto.OperacaoDto;
import com.everis.desafioBanco.Model.Conta;
import com.everis.desafioBanco.Service.ContaService;
import com.everis.desafioBanco.Service.OperacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Optional;

@RestController
public class OperacaoController {

    @Autowired
    private OperacaoService operacaoService;

    @Autowired
    private ContaService contaService;

    @PutMapping("/conta/sacar")
    public ResponseEntity<Conta> sacarValorDaConta(@RequestParam(name = "numeroDaConta") Long numeroDaConta){
        var conta = operacaoService.sacar(numeroDaConta, 10.00);
        return new ResponseEntity<>(conta, HttpStatus.OK);
    }

    @PutMapping("/conta/depositar")
    public ResponseEntity<Conta> depositarValorNaConta(@RequestParam(name = "numeroDaConta") Long numeroDaConta){
        var conta = operacaoService.depositar(numeroDaConta, 500);
        return new ResponseEntity<>(conta, HttpStatus.OK);
    }

    @GetMapping("/conta/consultar-saldo")
    public ResponseEntity<Optional<BigDecimal>> consultarSaldoDaConta(@RequestParam(name = "numeroDaConta") Long numeroDaConta){
        var saldoConta = operacaoService.consultarSaldo(numeroDaConta);
        return new ResponseEntity<Optional<BigDecimal>>(saldoConta, HttpStatus.OK);
    }
}
