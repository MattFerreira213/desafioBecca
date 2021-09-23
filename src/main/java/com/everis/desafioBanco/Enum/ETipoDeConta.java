package com.everis.desafioBanco.Enum;

import java.util.HashMap;
import java.util.Map;

public enum ETipoDeConta {

    PESSOA_FISICA("Pessoa Física"),
    PESSOA_JURIDICA("Pessoa Jurídica"),
    GOVERNAMENTAL("Governamental");

    private String descricao;

    private static final Map<String, ETipoDeConta> pegarTipoConta = new HashMap<>();

    static {
        for (ETipoDeConta tipoDeConta : ETipoDeConta.values()){
            pegarTipoConta.put(tipoDeConta.getDescricao(), tipoDeConta);
        }
    }

    ETipoDeConta(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public static ETipoDeConta pegarTipoContaPorDescricao(String descricao){
        return pegarTipoConta.get(descricao);
    }
}
