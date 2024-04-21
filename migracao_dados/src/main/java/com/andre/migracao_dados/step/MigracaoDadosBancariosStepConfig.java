package com.andre.migracao_dados.step;

import com.andre.migracao_dados.dominio.DadosBancarios;
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
public class MigracaoDadosBancariosStepConfig {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    // Cuidar com o valor do chunck, pois ele deve ser usado moderadamente
    @Bean
    public Step migracaoDadosBancariosStep(
            ItemReader<DadosBancarios> arquivoDadosBancariosReader,
            ItemWriter<DadosBancarios> bancoDadosBancariosWriter) {
        return new StepBuilder("migracaoDadosBancariosStep", jobRepository)
                .<DadosBancarios, DadosBancarios>chunk(10000, platformTransactionManager)
                .reader(arquivoDadosBancariosReader)
                .writer(bancoDadosBancariosWriter)
                .build();
    }
}
