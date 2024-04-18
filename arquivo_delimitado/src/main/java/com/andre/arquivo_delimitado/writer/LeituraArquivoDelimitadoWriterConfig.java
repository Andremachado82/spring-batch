package com.andre.arquivo_delimitado.writer;

import com.andre.arquivo_delimitado.dominio.Cliente;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LeituraArquivoDelimitadoWriterConfig {

    @Bean
    public ItemWriter<Cliente> leituraArquivoDelimitadoWriter() {
        return items -> items.forEach(System.out::println);
//        return items -> {
//            for (Cliente cLiente: items) {
//                if (cLiente.getNome().equals("Maria")) {
//                    throw new Exception();
//                } else {
//                    System.out.println(cLiente);
//                }
//            }
//        };
    }
}
