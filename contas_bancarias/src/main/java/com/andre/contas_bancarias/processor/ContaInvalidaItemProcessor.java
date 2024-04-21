package com.andre.contas_bancarias.processor;

import com.andre.contas_bancarias.dominio.Cliente;
import com.andre.contas_bancarias.dominio.Conta;
import org.springframework.batch.item.ItemProcessor;

public class ContaInvalidaItemProcessor implements ItemProcessor<Cliente, Conta> {
    @Override
    public Conta process(Cliente cliente) throws Exception {
        Conta conta = new Conta();
        conta.setClienteId(cliente.getEmail());
        return conta;
    }
}
