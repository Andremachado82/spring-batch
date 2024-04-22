package com.andre.fatura_cartao_credito.processor;

import com.andre.fatura_cartao_credito.dominio.Cliente;
import com.andre.fatura_cartao_credito.dominio.FaturaCartaoCredito;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.validator.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CarregarDadosClienteProcessor implements ItemProcessor<FaturaCartaoCredito, FaturaCartaoCredito> {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public FaturaCartaoCredito process(FaturaCartaoCredito faturaCartaoCredito) throws Exception {
       String uri =
               String.format("http://my-json-server.typicode.com/giuliana-bezerra/demo/profile/%d",
                       faturaCartaoCredito.getCliente().getId());
        ResponseEntity<Cliente> response = restTemplate.getForEntity(uri, Cliente.class);

        if (response.getStatusCode() != HttpStatus.OK)
            throw new ValidationException("Cliente não encontrado");

        faturaCartaoCredito.setCliente(response.getBody());
        return faturaCartaoCredito;
    }
}
