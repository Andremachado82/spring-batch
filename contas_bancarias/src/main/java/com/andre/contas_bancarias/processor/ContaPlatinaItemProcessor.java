package com.andre.contas_bancarias.processor;

import com.andre.contas_bancarias.dominio.Cliente;
import com.andre.contas_bancarias.dominio.Conta;
import com.andre.contas_bancarias.enums.TipoConta;
import org.springframework.batch.item.ItemProcessor;

public class ContaOuroItemProcessor implements ItemProcessor<Cliente, Conta> {

    @Override
    public Conta process(Cliente cliente) throws Exception {
        Conta conta = new Conta();
        conta.setClienteId(cliente.getEmail());
        conta.setTipo(TipoConta.OURO);
        conta.setLimite(1000.0);
        return conta;
    }
}
