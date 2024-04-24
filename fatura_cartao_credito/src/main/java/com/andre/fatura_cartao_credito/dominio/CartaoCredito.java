package com.andre.fatura_cartao_credito.dominio;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartaoCredito {

    private Integer numeroCartaoCredito;
    private Cliente cliente;
}
