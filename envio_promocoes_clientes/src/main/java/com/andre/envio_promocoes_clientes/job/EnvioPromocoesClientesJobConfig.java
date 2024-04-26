package com.andre.envio_promocoes_clientes.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EnvioPromocoesClientesJobConfig {

    @Autowired
    public JobRepository jobRepository;

    @Bean
    public Job envioPromocoesClientesJob(Step envioPromocoesClientesStep) {
        return new JobBuilder("envioPromocoesClientesJob", jobRepository)
                .start(envioPromocoesClientesStep)
                .incrementer(new RunIdIncrementer())
                .build();
    }
}
