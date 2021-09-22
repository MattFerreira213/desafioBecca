package com.everis.desafioBanco.Controller;

import com.everis.desafioBanco.Dto.OperacaoBancariaDto;
import com.everis.desafioBanco.Model.OperacaoBancaria;
import com.everis.desafioBanco.Repository.ContaRepository;
import com.everis.desafioBanco.Service.OperacaoService;
import com.everis.desafioBanco.Exceptions.ContaNaoEncontradaException;
import com.everis.desafioBanco.Exceptions.SaldoInsuficienteException;
import com.everis.desafioBanco.Exceptions.TransacaoNaoAutorizadaException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
        operacaoService.sacar(operacaoBancaria);
        return ResponseEntity.ok().body("Saque efetuado com sucesso");
    }

    @PostMapping("/conta/depositar")
    public ResponseEntity<?> depositar(@RequestBody @Valid OperacaoBancariaDto operacaoBancariaDto) {
        OperacaoBancaria operacaoBancaria = new OperacaoBancaria();
        BeanUtils.copyProperties(operacaoBancariaDto, operacaoBancaria);
        operacaoService.sacar(operacaoBancaria);
        return ResponseEntity.ok().body("Deposito efetuado com sucesso");
    }

//    @GetMapping("/conta/consultar-saldo")
//    public ResponseEntity<?> consultarSaldoDaConta(@RequestParam(name = "numeroDaConta") Long numeroDaConta) {
//        try {
//            var saldoConta = operacaoService.consultarSaldo(numeroDaConta);
//            return new ResponseEntity<>(saldoConta, HttpStatus.OK);
//        } catch (ContaNaoEncontradaException ex){
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
//        }
//    }
//
    @PostMapping("/conta/transferencia")
    public ResponseEntity<?> tranferencia(@RequestBody @Valid OperacaoBancaria operacaoBancaria) {
        operacaoService.tranferir(operacaoBancaria);
        return ResponseEntity.ok().body("Transação realizada com sucesso");
    }
}
