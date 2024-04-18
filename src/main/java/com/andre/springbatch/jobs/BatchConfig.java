package com.andre.springbatch.jobs;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BatchConfig {

    @Autowired
    private JobRepository jobRepository;

    @Bean
    public Job job(Step step) {
        return new JobBuilder("job", jobRepository).start(step)
                .incrementer(new RunIdIncrementer())
                .build();
    }
}
