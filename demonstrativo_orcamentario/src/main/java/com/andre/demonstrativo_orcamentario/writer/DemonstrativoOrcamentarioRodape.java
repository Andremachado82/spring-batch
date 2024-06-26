package com.andre.demonstrativo_orcamentario.writer;

import com.andre.demonstrativo_orcamentario.dominio.GrupoLancamento;
import org.springframework.batch.core.annotation.AfterChunk;
import org.springframework.batch.core.annotation.BeforeWrite;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.file.FlatFileFooterCallback;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Writer;
import java.text.NumberFormat;

@Component
public class DemonstrativoOrcamentarioRodape implements FlatFileFooterCallback {

    private Double totalGeral = 0.0;

    @Override
    public void writeFooter(Writer writer) throws IOException {
        writer.append("\n");
        writer.append(String.format("\t\t\t\t\t\t\t  Total: %s", NumberFormat.getCurrencyInstance().format(totalGeral)));
        writer.append(String.format("\n\t\t\t\t\t\t\t  Código de Autenticação: %s", "fkyew6868fewjfhjjewf"));
    }

    @BeforeWrite
    public void beforeWriter(Chunk<GrupoLancamento> chunk) {
        for (GrupoLancamento grupo : chunk.getItems()) {
            totalGeral += grupo.getTotal();
        }
    }

    @AfterChunk
    public void afterChunk(ChunkContext chunkContext) {
        totalGeral = 0.0;
    }
}
