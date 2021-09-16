package com.everis.desafioBanco.Utils.Exceptions;

public class ClienteJaCadastradoException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public ClienteJaCadastradoException(String msg) {
        super(msg);
    }
}
