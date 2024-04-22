package com.andre.fatura_cartao_credito.step;

import com.andre.fatura_cartao_credito.dominio.FaturaCartaoCredito;
import com.andre.fatura_cartao_credito.dominio.Transacao;
import com.andre.fatura_cartao_credito.reader.FaturaCartaoCreditoReaderConfig;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class FaturaCartaoCreditoStepConfig {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    @Bean
    public Step faturaCartaoCreditoStep(
            ItemStreamReader<Transacao> lerTransacoesReader,
            ItemProcessor<FaturaCartaoCredito, FaturaCartaoCredito> carregarDadosClienteProcessor,
            ItemWriter<FaturaCartaoCredito> escreverFaturaCartaoCredito) {
        return new StepBuilder("criacaoContasStep", jobRepository)
                .<FaturaCartaoCredito, FaturaCartaoCredito>chunk(1, platformTransactionManager)
                .reader(new FaturaCartaoCreditoReaderConfig(lerTransacoesReader))
                .processor(carregarDadosClienteProcessor)
                .writer(escreverFaturaCartaoCredito)
                .build();
    }
}
