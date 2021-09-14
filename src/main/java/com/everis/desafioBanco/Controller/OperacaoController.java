package com.everis.desafioBanco.Controller;

import com.everis.desafioBanco.Repository.ContaRepository;
import com.everis.desafioBanco.Service.OperacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class OperacaoController {

    @Autowired
    private OperacaoService operacaoService;

    @Autowired
    private ContaRepository contaRepository;

    @PutMapping("/conta/sacar")
    public ResponseEntity<String> sacar(@RequestParam(name = "numeroDaConta") Long numeroDaConta,
                                        @RequestParam(name = "valorDeSaque") double valorDeSaque) {
        var conta = operacaoService.sacar(numeroDaConta, valorDeSaque);
        return new ResponseEntity<>(conta, HttpStatus.OK);
    }

    @PutMapping("/conta/depositar")
    public ResponseEntity<String> depositar(@RequestParam(name = "numeroDaConta") Long numeroDaConta,
                                            @RequestParam(name = "valorDeDeposito") double valorDeDeposito) {
        var conta = operacaoService.depositar(numeroDaConta, valorDeDeposito);
        return new ResponseEntity<>(conta, HttpStatus.OK);
    }

    @GetMapping("/conta/consultar-saldo")
    public ResponseEntity<BigDecimal> consultarSaldoDaConta(@RequestParam(name = "numeroDaConta") Long numeroDaConta) {
        var saldoConta = operacaoService.consultarSaldo(numeroDaConta);
        return new ResponseEntity<>(saldoConta, HttpStatus.OK);
    }

    @PutMapping("/conta/transferencia")
    public ResponseEntity<String> tranferencia(@RequestParam(name = "numeroDaContaOrigem") Long numeroDaContaO,
                                               @RequestParam(name = "numeroDaContaDestino") Long numeroDaContaD,
                                               @RequestParam(name = "valorDeTransferencia") double valorDeTransferencia) {

        var response = operacaoService.tranferir(numeroDaContaO, numeroDaContaD, valorDeTransferencia);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
