package com.andre.jdbc_cursor_reader.step;

import com.andre.jdbc_cursor_reader.dominio.Cliente;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class JdbcCursorReaderStepConfig {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private PlatformTransactionManager platformTransactionManager;


    @Bean
    public Step jdbcCursorReaderStep(ItemReader<Cliente> jdbcCursorReader,
                                              ItemWriter<Cliente> jdbcCursorWriter) {
        return new StepBuilder("jdbcCursorReaderStep", jobRepository)
                .<Cliente, Cliente>chunk(4, platformTransactionManager)
                .reader(jdbcCursorReader)
                .writer(jdbcCursorWriter)
                .faultTolerant()
                .skip(Exception.class)
                .skipLimit(2)
                .build();
    }
}
