package com.andre.processador_script.writer;

import com.andre.processador_script.dominio.Cliente;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProcessadorScriptWriterConfig {

    @Bean
    public ItemWriter<Cliente> processadorScriptWriter() {
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
