package com.andre.arquivo_delimitado_writer.writer;

import com.andre.arquivo_delimitado_writer.dominio.Cliente;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.WritableResource;

@Configuration
public class LeituraArquivoDelimitadoWriterConfig {

    @StepScope
    @Bean
    public FlatFileItemWriter<Cliente> escritaArquivoDelimitadoWriter(
            @Value("#{jobParameters['arquivoClienteSaida']}") Resource arquivoClienteSaida) {
        return new FlatFileItemWriterBuilder<Cliente>()
                .name("escritaArquivoDelimitadoWriter")
                .resource((WritableResource) arquivoClienteSaida)
                .delimited()
                .names("nome", "sobrenome", "idade", "email")
                .build();
    }
}
