package com.everis.desafioBanco.Utils;

public class Operacao {

    public static double sacar(double valorSaque, double valorSaldo) {
        double novoValorSaldo = 0.0;

        if (valorSaque < valorSaldo + 10) {
            novoValorSaldo = valorSaldo - valorSaque;
        }
        return novoValorSaldo;
    }

    public static double depositar(double valorSaque, double valorSaldo) {
        double novoValorSaldo = valorSaldo + valorSaque;
        return novoValorSaldo;
    }
}
