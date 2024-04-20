package com.andre.arquivo_largura_fixa_writer.writer;

import com.andre.arquivo_largura_fixa_writer.dominio.Cliente;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.WritableResource;

@Configuration
public class LeituraArquivoLarguraFixaWriterConfig {

    @StepScope
    @Bean
    public FlatFileItemWriter<Cliente> escritaArquivoLarguraFixaWriter(
            @Value("#{jobParameters['arquivoClienteSaida']}") Resource arquivoClienteSaida
            ) {
        return new FlatFileItemWriterBuilder<Cliente>()
                .name("escritaArquivoLarguraFixaWriter")
                .resource((WritableResource) arquivoClienteSaida)
                .formatted()
                .format("%-9s %-9s %-2s %-19s")
                .names("nome", "sobrenome", "idade", "email")
                .build();
    }
}
