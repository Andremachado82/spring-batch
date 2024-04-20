package com.andre.demonstrativo_orcamentario.reader;

import com.andre.demonstrativo_orcamentario.dominio.GrupoLancamento;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.builder.MultiResourceItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class DemonstrativoOrcamentarioReaderConfig {

    @StepScope
    @Bean
    public MultiResourceItemReader<GrupoLancamento> demonstrativoOrcamentarioReader(
            @Value("#{jobParameters['arquivosLancamento']}") Resource[] arquivosLancamento) {
        return new MultiResourceItemReaderBuilder<GrupoLancamento>()
                .name("demonstrativoOrcamentarioReader")
                .resources(arquivosLancamento)
                .build();
    }
}
