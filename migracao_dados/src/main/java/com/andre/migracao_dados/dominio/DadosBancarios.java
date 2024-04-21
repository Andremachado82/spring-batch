package com.andre.migracao_dados.dominio;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DadosBancarios {

    private Integer id;
    private Integer pessoaId;
    private Integer agencia;
    private Integer conta;
    private Integer banco;
}
