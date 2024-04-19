package com.andre.jdbc_paging_reader.writer;

import com.andre.jdbc_paging_reader.dominio.Cliente;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JdbcPagingWriterConfig {

    @Bean
    public ItemWriter<Cliente> jdbcPagingWriter() {
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
