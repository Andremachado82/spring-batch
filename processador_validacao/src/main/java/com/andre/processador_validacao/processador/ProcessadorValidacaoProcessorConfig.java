package com.andre.processador_validacao.processador;

import com.andre.processador_validacao.dominio.Cliente;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.validator.BeanValidatingItemProcessor;
import org.springframework.batch.item.validator.ValidatingItemProcessor;
import org.springframework.batch.item.validator.ValidationException;
import org.springframework.batch.item.validator.Validator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;

@Configuration
@SuppressWarnings("ALL")
public class ProcessadorValidacaoProcessorConfig {

    private Set<String> emails = new HashSet<>();

    @Bean
    public ItemProcessor<Cliente, Cliente> procesadorValidacaoProcessor() {
//        BeanValidatingItemProcessor<Cliente> processor = new BeanValidatingItemProcessor<>();
        ValidatingItemProcessor<Cliente> processor = new ValidatingItemProcessor<>();
        processor.setValidator(validator());
        processor.setFilter(true);
        return processor;

    }

    private Validator<Cliente> validator() {
        return new Validator<Cliente>() {

            @Override
            public void validate(Cliente cliente) throws ValidationException {
                if (emails.contains(cliente.getEmail()))
                   throw new ValidationException(String.format("O cliente %s já foi processado.", cliente.getEmail()));
                emails.add(cliente.getEmail());
            }
        };
    }
}
