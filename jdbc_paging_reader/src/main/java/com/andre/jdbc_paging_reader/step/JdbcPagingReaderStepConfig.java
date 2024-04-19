package com.andre.jdbc_paging_reader.step;

import com.andre.jdbc_paging_reader.dominio.Cliente;
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
public class JdbcPagingReaderStepConfig {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private PlatformTransactionManager platformTransactionManager;


    @Bean
    public Step jdbcPagingReaderStep(ItemReader<Cliente> jdbcPagingReader,
                                              ItemWriter<Cliente> jdbcPagingWriter) {
        return new StepBuilder("jdbcPagingReaderStep", jobRepository)
                .<Cliente, Cliente>chunk(4, platformTransactionManager)
                .reader(jdbcPagingReader)
                .writer(jdbcPagingWriter)
                .build();
    }
}
