package com.andre.fatura_cartao_credito.writer;

import com.andre.fatura_cartao_credito.dominio.FaturaCartaoCredito;
import org.springframework.batch.core.annotation.AfterChunk;
import org.springframework.batch.core.annotation.BeforeWrite;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.file.FlatFileFooterCallback;

import java.io.IOException;
import java.io.Writer;
import java.text.NumberFormat;

public class TotalTransacoesFooterCallback implements FlatFileFooterCallback {

    private Double total = 0.0;

    @Override
    public void writeFooter(Writer writer) throws IOException {
        writer.append(String.format("\n%121s", "Total: " + NumberFormat.getCurrencyInstance().format(total)));
    }

    @BeforeWrite
    public void beforeWriter(Chunk<FaturaCartaoCredito> faturas) {
        for (FaturaCartaoCredito fatura: faturas.getItems()) {
            total += fatura.getTotal();
        }
    }

    @AfterChunk
    public void afterChunk(ChunkContext chunkContext) {
        total = 0.0;
    }
}
