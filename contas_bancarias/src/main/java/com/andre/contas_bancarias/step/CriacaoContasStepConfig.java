package com.andre.contas_bancarias.step;

import com.andre.contas_bancarias.dominio.Cliente;
import com.andre.contas_bancarias.dominio.Conta;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.support.CompositeItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class CriacaoContasStepConfig {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private PlatformTransactionManager platformTransactionManager;


    @Bean
    public Step criacaoContasStep(
            ItemReader<Cliente> leituraClientesReader,
            ItemProcessor<Cliente, Conta> geracaoContaProcessor,
            CompositeItemWriter<Conta> compositeItemWriter) {
        return new StepBuilder("criacaoContasStep", jobRepository)
                .<Cliente, Conta>chunk(100, platformTransactionManager)
                .reader(leituraClientesReader)
                .processor(geracaoContaProcessor)
                .writer(compositeItemWriter)
                .build();
    }
}
