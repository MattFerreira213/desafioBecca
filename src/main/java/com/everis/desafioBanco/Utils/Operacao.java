package com.everis.desafioBanco.Utils;

import java.math.BigDecimal;

public class Operacao {

    public static double sacar(double valorSaque, double valorSaldo){
        double novoValorSaldo = 0.0;
        int cont = 0;
        if (cont < 5) {
            if (valorSaque < valorSaldo + 10) {
                novoValorSaldo = valorSaldo - valorSaque;
            }
            cont++;
        } else {
            if (valorSaque < valorSaldo + 10) {
                novoValorSaldo = valorSaldo - valorSaque - 10;
            }
            cont++;
        }
        return novoValorSaldo;
    }

    public static double depositar(double valorSaque, double valorSaldo){
        double novoValorSaldo = valorSaldo + valorSaque;;
        return novoValorSaldo;
    }

    public static void tranferir(BigDecimal valor){}

    public static void consultarExtrato(){}


}
