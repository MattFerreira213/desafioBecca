package com.everis.desafioBanco.Exceptions;

public class ContaExistenteException extends RuntimeException{

    public ContaExistenteException(String msg){
        super(msg);
    }
}
