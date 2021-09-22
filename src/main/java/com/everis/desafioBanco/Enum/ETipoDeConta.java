package com.everis.desafioBanco.Enum;

public enum ETipoDeConta {

    PESSOA_FISICA("Pessoa Física"),
    PESSOA_JURIDICA("Pessoa Jurídica"),
    GOVERNAMENTAL("Governamental");

    private String descricao;

    ETipoDeConta(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
