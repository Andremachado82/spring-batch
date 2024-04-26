package com.andre.envio_promocoes_clientes.step;

import com.andre.envio_promocoes_clientes.dominio.InteresseProdutoCliente;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class EnvioPromocoesClientesStepConfig {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    @Bean
    public Step envioPromocoesClientesStep(
                ItemReader<InteresseProdutoCliente> lerInteresseProdutoReader,
                ItemProcessor<InteresseProdutoCliente, SimpleMailMessage> processarEmailProdutoClienteProcessor,
                ItemWriter<SimpleMailMessage> enviarEmailProdutoClienteWriter
                ) {
        return new StepBuilder("envioPromocoesClientesStep", jobRepository)
                .<InteresseProdutoCliente, SimpleMailMessage>chunk(1, platformTransactionManager)
                .reader(lerInteresseProdutoReader)
                .processor(processarEmailProdutoClienteProcessor)
                .writer(enviarEmailProdutoClienteWriter)
                .build();
    }
}
