package com.andre.fatura_cartao_credito.dominio;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Transacao {

    private Integer id;
    private CartaoCredito cartaoCredito;
    private String descricao;
    private Double valor;
    private Date dtTransacao;
}
