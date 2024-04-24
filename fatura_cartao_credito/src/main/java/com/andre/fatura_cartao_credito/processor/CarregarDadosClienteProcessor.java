package com.andre.fatura_cartao_credito.processor;

import com.andre.fatura_cartao_credito.dominio.Cliente;
import com.andre.fatura_cartao_credito.dominio.FaturaCartaoCredito;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.validator.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CarregarDadosClienteProcessor implements ItemProcessor<FaturaCartaoCredito, FaturaCartaoCredito> {

    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public FaturaCartaoCredito process(FaturaCartaoCredito faturaCartaoCredito) throws Exception {
        MappingJackson2HttpMessageConverter mapper = new MappingJackson2HttpMessageConverter();

        mapper.setSupportedMediaTypes(List.of(MediaType.APPLICATION_JSON));
        restTemplate.getMessageConverters().add(mapper);

       String uri =
               String.format("http://my-json-server.typicode.com/giuliana-bezerra/demo/profile/%s",
                       faturaCartaoCredito.getCliente().getId());
        ResponseEntity<Cliente> response = restTemplate.getForEntity(uri, Cliente.class);

        if (response.getStatusCode() != HttpStatus.OK)
            throw new ValidationException("Cliente não encontrado");

        faturaCartaoCredito.setCliente(response.getBody());
        return faturaCartaoCredito;
    }
}
