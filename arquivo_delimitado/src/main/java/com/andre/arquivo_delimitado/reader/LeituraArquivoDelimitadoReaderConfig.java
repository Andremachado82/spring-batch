package com.andre.arquivo_delimitado.reader;

import com.andre.arquivo_delimitado.dominio.Cliente;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class LeituraArquivoDelimitadoReaderConfig {

    @StepScope
    @Bean
    public FlatFileItemReader<Cliente> leituraArquivoDelimitadoReader(
            @Value("#{jobParameters['arquivoClientes']}") Resource arqClientes) {
        return new FlatFileItemReaderBuilder<Cliente>()
                .name("leituraArquivoDelimitadoReader")
                .resource(arqClientes)
                .delimited()
                .names(new String[] {"nome", "sobrenome", "idade", "email"})
                .targetType(Cliente.class)
                .build();
    }
}
