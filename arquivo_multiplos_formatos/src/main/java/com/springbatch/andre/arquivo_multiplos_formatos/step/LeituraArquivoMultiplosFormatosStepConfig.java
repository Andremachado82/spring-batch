package com.springbatch.andre.arquivo_multiplos_formatos.step;

import com.springbatch.andre.arquivo_multiplos_formatos.dominio.Cliente;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@SuppressWarnings("ALL")
@Configuration
public class LeituraArquivoMultiplosFormatosStepConfig {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private PlatformTransactionManager platformTransactionManager;


    @Bean
    public Step leituraArquivoMultiplosFormatosStep(
            MultiResourceItemReader<Cliente> multiplosArquivosClienteTransacaoReader,
            ItemWriter leituraArquivoMultiplosFormatosItemWriter) {
        return new StepBuilder("leituraArquivoMultiplosFormatosStep", jobRepository)
                .chunk(1, platformTransactionManager)
                .reader(multiplosArquivosClienteTransacaoReader)
                .writer(leituraArquivoMultiplosFormatosItemWriter)
                .build();
    }
}
