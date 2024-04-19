package com.andre.jdbc_cursor_reader.writer;

import com.andre.jdbc_cursor_reader.dominio.Cliente;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JdbcCursorWriterConfig {

    @Bean
    public ItemWriter<Cliente> jdbcCursorWriter() {
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
