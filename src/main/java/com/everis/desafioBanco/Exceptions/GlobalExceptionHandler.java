package com.everis.desafioBanco.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private String erros;

    @ExceptionHandler(ClienteExistenteException.class)
    public ResponseEntity<StandardError> handleClienteExistenteException(ClienteExistenteException ex){
        erros = ex.getMessage();
        StandardError msg = new StandardError(erros);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(msg);
    }

    @ExceptionHandler(CpfNaoEncontradoException.class)
    public ResponseEntity<StandardError> handleCpfNaoEncontradoException(CpfNaoEncontradoException ex){
        erros = ex.getMessage();
        StandardError msg2 = new StandardError(erros);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg2);
    }

    @ExceptionHandler(ContaExistenteException.class)
    public  ResponseEntity<StandardError> hadleContaExistenteException(ContaExistenteException ex){
        erros = ex.getMessage();
        StandardError msg3 = new StandardError(erros);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(msg3);
    }

    @ExceptionHandler(ContaNaoEncontradaException.class)
    public ResponseEntity<StandardError> handleContaNaoEncontradoException(ContaNaoEncontradaException ex){
        erros = ex.getMessage();
        StandardError msg4 = new StandardError(erros);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg4);
    }

    @ExceptionHandler(SaldoInsuficienteException.class)
    public ResponseEntity<StandardError> handleSaldoInsuficienteException(SaldoInsuficienteException ex){
        erros = ex.getMessage();
        StandardError msg5 = new StandardError(erros);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg5);
    }

    @ExceptionHandler(TransacaoNaoAutorizadaException.class)
    public ResponseEntity<StandardError> handleTransacaoNaoAutorizadaException(TransacaoNaoAutorizadaException ex){
        erros = ex.getMessage();
        StandardError msg6 = new StandardError(erros);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg6);
    }


}
