package com.springbatch.andre.arquivo_multiplos_formatos.reader;

import com.springbatch.andre.arquivo_multiplos_formatos.dominio.Cliente;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@SuppressWarnings("ALL")
@Configuration
public class ArquivoMultiplosFormatosReaderConfig {

    @StepScope
    @Bean
    public FlatFileItemReader<Cliente> arquivoMultiplosFormatosItemReader(
            @Value("#{jobParameters['arquivoClientes']}") Resource arqClientes, LineMapper lineMapper) {
        return new FlatFileItemReaderBuilder<Cliente>()
                .name("arquivoMultiplosFormatosItemReader")
                .resource(arqClientes)
                .lineMapper(lineMapper)
                .build();
    }
}
