package com.andre.aula_teste.step;

import com.andre.aula_teste.dominio.Lancamento;
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
public class LeituraArquivoDelimitadoStepConfig {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private PlatformTransactionManager platformTransactionManager;


    @Bean
    public Step leituraArquivoDelimitadoStep(ItemReader<Lancamento> leituraArquivoDelimitadoReader,
                                              ItemWriter<Lancamento> leituraArquivoDelimitadoWriter) {
        return new StepBuilder("leituraArquivoDelimitadoStep", jobRepository)
                .<Lancamento, Lancamento>chunk(1, platformTransactionManager)
                .reader(leituraArquivoDelimitadoReader)
                .writer(leituraArquivoDelimitadoWriter)
                .build();
    }
}
