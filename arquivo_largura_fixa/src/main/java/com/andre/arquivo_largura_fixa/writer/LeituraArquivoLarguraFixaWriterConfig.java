package com.andre.arquivo_largura_fixa.writer;

import com.andre.arquivo_largura_fixa.dominio.Cliente;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LeituraArquivoLarguraFixaWriterConfig {

    @Bean
    public ItemWriter<Cliente> leituraArquivoLarguraFixaWriter() {
        return items -> items.forEach(System.out::println);
    }
}
