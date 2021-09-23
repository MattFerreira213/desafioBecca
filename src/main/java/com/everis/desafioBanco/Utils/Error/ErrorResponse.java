package com.everis.desafioBanco.Utils.Error;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ErrorResponse {

    private final String message;
    private final String campo;
}
