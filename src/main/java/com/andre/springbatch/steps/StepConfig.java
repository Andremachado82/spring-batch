package com.andre.springbatch.steps;

import com.andre.springbatch.tasklets.Tasklet;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class StepConfig {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    @Autowired
    private Tasklet tasklet;


    @Bean
    public Step step() {
        return new StepBuilder("step", jobRepository)
                .<Integer, String>chunk(10, platformTransactionManager)
                .reader(tasklet.contaAteDezReader())
                .processor(tasklet.parOuImparProcessor())
                .writer(tasklet.imprimeWriter())
                .build();
    }
}
