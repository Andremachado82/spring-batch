package com.andre.migracao_dados.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

@Configuration
public class MigracaoDadosJobConfig {

    @Autowired
    public JobRepository jobRepository;

    @Bean
    public Job migracaoDadosJob(
            @Qualifier("migracaoPessoaStep") Step migracaoPessoasStep,
            @Qualifier("migracaoDadosBancariosStep") Step migracaoDadosBancariosStep) {
        return new JobBuilder("migracaoDadosJob", jobRepository)
                .start(startStepsParalelos(migracaoPessoasStep, migracaoDadosBancariosStep))
                .end()
                .incrementer(new RunIdIncrementer())
                .build();
    }
    // configuração de execução em threads
    private Flow startStepsParalelos(Step migracaoPessoasStep, Step migracaoDadosBancariosStep) {
        Flow migrarDadosBancariosFlow = new FlowBuilder<Flow>("migracaoDadosBancariosStep")
                .start(migracaoDadosBancariosStep)
                .build();

        Flow stepsParalelos = new FlowBuilder<Flow>("stepsParalelosFlow")
                .start(migracaoPessoasStep)
                .split(new SimpleAsyncTaskExecutor())
                .add(migrarDadosBancariosFlow)
                .build();
        return stepsParalelos;
    }
}
