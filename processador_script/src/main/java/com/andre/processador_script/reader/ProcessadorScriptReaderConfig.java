package com.andre.processador_script.reader;

import com.andre.processador_script.dominio.Cliente;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class ProcessadorScriptReaderConfig {

    @StepScope
    @Bean
    public FlatFileItemReader<Cliente> processadorScriptReader(
            @Value("#{jobParameters['arquivoClientes']}") Resource arqClientes) {
        return new FlatFileItemReaderBuilder<Cliente>()
                .name("processadorScriptReader")
                .resource(arqClientes)
                .delimited()
                .names(new String[] {"nome", "idade", "email"})
                .targetType(Cliente.class)
                .build();
    }
}
