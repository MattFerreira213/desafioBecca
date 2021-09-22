package com.everis.desafioBanco.Exceptions;

public class ContaNaoEncontradaException extends RuntimeException{

    public ContaNaoEncontradaException(String msg){
        super(msg);
    }
}
