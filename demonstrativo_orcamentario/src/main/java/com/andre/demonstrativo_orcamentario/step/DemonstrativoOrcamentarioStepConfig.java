package com.andre.demonstrativo_orcamentario.step;

import com.andre.demonstrativo_orcamentario.dominio.GrupoLancamento;
import com.andre.demonstrativo_orcamentario.reader.GrupoLancamentoReader;
import com.andre.demonstrativo_orcamentario.writer.DemonstrativoOrcamentarioRodape;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.file.MultiResourceItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class DemonstrativoOrcamentarioStepConfig {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private PlatformTransactionManager platformTransactionManager;


    @Bean
    public Step leituraArquivoDelimitadoStep(
            // Esse aqui lê dos arquivos
            //MultiResourceItemReader<GrupoLancamento> demonstrativoOrcamentarioReader,
            // Esse aqui lê do banco de dados
            GrupoLancamentoReader demonstrativoOrcamentarioReader,
            MultiResourceItemWriter<GrupoLancamento> demonstrativoOrcamentarioWriter,
            DemonstrativoOrcamentarioRodape rodapeCallBack) {
        return new StepBuilder("demonstrativoOrcamentarioStep", jobRepository)
                .<GrupoLancamento, GrupoLancamento>chunk(1, platformTransactionManager)
                .reader(demonstrativoOrcamentarioReader)
                .writer(demonstrativoOrcamentarioWriter)
                .listener(rodapeCallBack)
                .build();
    }
}
