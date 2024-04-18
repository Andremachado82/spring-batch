package com.springbatch.andre.arquivo_multiplos_formatos.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ArquivoMultiplosFormatosJobConfig {

    @Autowired
    public JobRepository jobRepository;

    @Bean
    public Job arquivoMultiplosFormatosJob(Step leituraArquivoMultiplosFormatosStep) {
        return new JobBuilder("arquivoMultiplosFormatosJob", jobRepository)
                .start(leituraArquivoMultiplosFormatosStep)
                .incrementer(new RunIdIncrementer())
                .build();
    }
}
