package com.andre.fatura_cartao_credito.dominio;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class FaturaCartaoCredito {

    private Cliente cliente;
    private CartaoCredito cartaoCredito;
    private List<Transacao> transacoes = new ArrayList<>();

    public Double getTotal() {
        return transacoes.stream().mapToDouble(Transacao::getValor)
                .reduce(0.0, Double::sum);
    }
}
