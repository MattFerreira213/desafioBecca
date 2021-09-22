package com.everis.desafioBanco.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
public class StandardError {

    private String menssagem;
}
