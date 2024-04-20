package com.andre.contas_bancarias.processor;

import com.andre.contas_bancarias.dominio.Cliente;
import com.andre.contas_bancarias.dominio.Conta;
import com.andre.contas_bancarias.enums.TipoConta;
import org.springframework.batch.item.ItemProcessor;

public class ContaPlatinaItemProcessor implements ItemProcessor<Cliente, Conta> {

    @Override
    public Conta process(Cliente cliente) throws Exception {
        Conta conta = new Conta();
        conta.setClienteId(cliente.getEmail());
        conta.setTipo(TipoConta.PLATINA);
        conta.setLimite(2500.0);
        return conta;
    }
}
