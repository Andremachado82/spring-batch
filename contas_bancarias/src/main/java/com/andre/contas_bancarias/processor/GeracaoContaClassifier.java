package com.andre.contas_bancarias.processor;

import com.andre.contas_bancarias.dominio.Cliente;
import com.andre.contas_bancarias.dominio.Conta;
import com.andre.contas_bancarias.enums.TipoConta;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.classify.Classifier;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("serial")
public class GeracaoContaClassifier implements Classifier<Cliente, ItemProcessor<?, ? extends Conta>> {

    private static final Map<TipoConta, ItemProcessor<Cliente, Conta>> processadores = new HashMap<TipoConta, ItemProcessor<Cliente, Conta>>() {{
        put(TipoConta.PRATA, new ContaPrataItemProcessor());
        put(TipoConta.OURO, new ContaOuroItemProcessor());
        put(TipoConta.PLATINA, new ContaPlatinaItemProcessor());
        put(TipoConta.DIAMANTE, new ContaDiamanteItemProcessor());
    }};

    @Override
    public ItemProcessor<Cliente, Conta> classify(Cliente cliente) {
        TipoConta tipoConta = TipoConta.fromFaixaSalarial(cliente.getFaixaSalarial());
        return processadores.get(tipoConta);
    }
}
