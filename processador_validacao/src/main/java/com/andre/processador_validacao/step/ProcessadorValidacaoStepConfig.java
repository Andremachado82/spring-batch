package com.andre.processador_validacao.step;

import com.andre.processador_validacao.dominio.Cliente;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class ProcessadorValidacaoStepConfig {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private PlatformTransactionManager platformTransactionManager;


    @Bean
    public Step processadorValidacaoStep(
            ItemReader<Cliente> processadorValidacaoReader,
            ItemProcessor<Cliente, Cliente> processadorValidacaoProcessor,
            ItemWriter<Cliente> processadorValidacaoWriter
            ) {
        return new StepBuilder("leituraArquivoDelimitadoStep", jobRepository)
                .<Cliente, Cliente>chunk(4, platformTransactionManager)
                .reader(processadorValidacaoReader)
                .processor(processadorValidacaoProcessor)
                .writer(processadorValidacaoWriter)
                .build();
    }
}
