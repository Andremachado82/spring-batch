package com.andre.fatura_cartao_credito.dominio;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class FaturaCartaoCredito {

    private Cliente cliente;
    private CartaoCredito cartaoCredito;
    private List<Transacao> transacoes;
}
