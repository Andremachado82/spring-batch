package com.andre.contas_bancarias.writer;

import com.andre.contas_bancarias.dominio.Conta;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class ClienteInvalidaWriterConfig {

    @Bean
    public FlatFileItemWriter<Conta> clienteInvalidoWriter() {
        return new FlatFileItemWriterBuilder<Conta>()
                .name("clienteInvalidoWriter")
                .resource(new FileSystemResource("./files/clientesInvalidos.txt"))
                .delimited()
                .names("clienteId")
                .build();
    }
}
