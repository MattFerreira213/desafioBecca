package com.everis.desafioBanco.Utils.Exceptions;

public class ContaJaExistenteException extends RuntimeException{

    public ContaJaExistenteException(String msg){
        super(msg);
    }
}
