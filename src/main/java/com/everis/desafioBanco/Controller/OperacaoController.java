package com.everis.desafioBanco.Controller;

import com.everis.desafioBanco.Dto.OperacaoBancariaDto;
import com.everis.desafioBanco.Model.OperacaoBancaria;
import com.everis.desafioBanco.Repository.ContaRepository;
import com.everis.desafioBanco.Service.OperacaoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
public class OperacaoController {

    @Autowired
    private OperacaoService operacaoService;

    @Autowired
    private ContaRepository contaRepository;

    @PostMapping("/conta/sacar")
    public ResponseEntity<?> sacar(@RequestBody @Valid OperacaoBancariaDto operacaoBancariaDto) {
        OperacaoBancaria operacaoBancaria = new OperacaoBancaria();
        BeanUtils.copyProperties(operacaoBancariaDto, operacaoBancaria);
        var msg = operacaoService.sacar(operacaoBancaria);
        return ResponseEntity.status(HttpStatus.CREATED).body(msg);
    }

    @PostMapping("/conta/depositar")
    public ResponseEntity<?> depositar(@RequestBody @Valid OperacaoBancariaDto operacaoBancariaDto) {
        OperacaoBancaria operacaoBancaria = new OperacaoBancaria();
        BeanUtils.copyProperties(operacaoBancariaDto, operacaoBancaria);
        operacaoService.depositar(operacaoBancaria);
        var mensagem = Map.entry("mensagem", "Deposito efetuado com sucesso");
        return ResponseEntity.status(HttpStatus.CREATED).body(mensagem);
    }

    @PostMapping("/conta/transferencia")
    public ResponseEntity<?> tranferencia(@RequestBody @Valid OperacaoBancariaDto operacaoBancariaDto) {
        OperacaoBancaria operacaoBancaria = new OperacaoBancaria();
        BeanUtils.copyProperties(operacaoBancariaDto, operacaoBancaria);
        operacaoService.tranferir(operacaoBancaria);
        var mensagem = Map.entry("mensagem", "Transação realizada com sucesso");
        return ResponseEntity.status(HttpStatus.CREATED).body(mensagem);
    }

    @GetMapping("/conta/consultar-saldo")
    public ResponseEntity<?> consultarSaldoDaConta(@RequestParam(name = "numeroDaConta") Long numeroDaConta) {
        var saldoConta = operacaoService.consultarSaldo(numeroDaConta);
        return ResponseEntity.ok().body(saldoConta);
    }

    @GetMapping("/conta/extrato")
    public  ResponseEntity<?> consultarExtrato(@RequestParam(name = "numeroDaConta") Long numeroDaConta){
        var operacoes = operacaoService.extrato(numeroDaConta);
        return ResponseEntity.ok(operacoes);
    }
}
