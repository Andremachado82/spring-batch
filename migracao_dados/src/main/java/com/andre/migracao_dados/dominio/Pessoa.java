package com.andre.migracao_dados.dominio;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class Pessoa {

    private Integer id;
    private String nome;
    private String email;
    private Date dataNascimento;
    private Integer idade;
}
