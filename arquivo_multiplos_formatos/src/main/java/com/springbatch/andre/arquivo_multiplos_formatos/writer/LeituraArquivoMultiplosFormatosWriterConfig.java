package com.springbatch.andre.arquivo_multiplos_formatos.writer;

import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SuppressWarnings("ALL")
@Configuration
public class LeituraArquivoMultiplosFormatosWriterConfig {

    @Bean
    public ItemWriter leituraArquivoDelimitadoWriter() {
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
