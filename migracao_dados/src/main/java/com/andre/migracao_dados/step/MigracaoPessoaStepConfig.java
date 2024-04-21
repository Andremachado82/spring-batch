package com.andre.migracao_dados.step;

import com.andre.migracao_dados.dominio.Pessoa;
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
public class MigracaoPessoaStepConfig {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private PlatformTransactionManager platformTransactionManager;


    @Bean
    public Step migracaoPessoaStep(
            ItemReader<Pessoa> arquivoPessoaReader,
            ItemWriter<Pessoa> bancoPessoaWriter) {
        return new StepBuilder("migracaoPessoaStep", jobRepository)
                .<Pessoa, Pessoa>chunk(1, platformTransactionManager)
                .reader(arquivoPessoaReader)
                .writer(bancoPessoaWriter)
                .build();
    }
}
