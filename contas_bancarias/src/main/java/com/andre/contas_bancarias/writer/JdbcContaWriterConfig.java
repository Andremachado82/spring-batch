package com.andre.contas_bancarias.writer;

import com.andre.contas_bancarias.dominio.Conta;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ImpressaoContaWriterConfig {

    @Bean
    public ItemWriter<Conta> impressaoContaWriter() {
        return contas -> contas.forEach(System.out::println);
    }
}
