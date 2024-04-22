package com.andre.fatura_cartao_credito.dominio;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.logging.log4j.util.Strings;

import java.util.Date;

@Getter
@Setter
@ToString
public class CartaoCredito {

    private Integer numeroCartaoCredito;
    private Cliente cliente;
}
