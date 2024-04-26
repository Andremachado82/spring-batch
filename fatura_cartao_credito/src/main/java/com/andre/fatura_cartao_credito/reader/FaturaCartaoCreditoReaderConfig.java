package com.andre.fatura_cartao_credito.reader;

import com.andre.fatura_cartao_credito.dominio.CartaoCredito;
import com.andre.fatura_cartao_credito.dominio.Cliente;
import com.andre.fatura_cartao_credito.dominio.FaturaCartaoCredito;
import com.andre.fatura_cartao_credito.dominio.Transacao;
import org.springframework.batch.item.*;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class FaturaCartaoCreditoReaderConfig implements ItemStreamReader<FaturaCartaoCredito> {

    private ItemStreamReader<Transacao> delegate;
    private Transacao transacaoAtual;

    @Override
    public FaturaCartaoCredito read() throws Exception {
        if (transacaoAtual == null)
            transacaoAtual = delegate.read();
        FaturaCartaoCredito faturaCartaoCredito = null;
        Transacao transacao = transacaoAtual;
        transacaoAtual = null;

        if (transacao != null) {
            faturaCartaoCredito = new FaturaCartaoCredito();
            faturaCartaoCredito.setCartaoCredito(transacao.getCartaoCredito());
            faturaCartaoCredito.setCliente(transacao.getCartaoCredito().getCliente());
            faturaCartaoCredito.getTransacoes().add(transacao);

            while (isTransacaoRelacionada(transacao))
                faturaCartaoCredito.getTransacoes().add(transacaoAtual);
        }
        return faturaCartaoCredito;
    }

    private boolean isTransacaoRelacionada(Transacao transacao) throws Exception {
        return peek() != null && Objects.equals(transacao.getCartaoCredito().getNumeroCartaoCredito(),
                transacaoAtual.getCartaoCredito().getNumeroCartaoCredito());
    }

    private Transacao peek() throws Exception {
        transacaoAtual = delegate.read();
        return transacaoAtual;
    }

    public FaturaCartaoCreditoReaderConfig(ItemStreamReader<Transacao> delegate) {
        this.delegate = delegate;
    }

    @Override
    public void open(ExecutionContext executionContext) throws ItemStreamException {
        delegate.open(executionContext);
    }

    @Override
    public void update(ExecutionContext executionContext) throws ItemStreamException {
        delegate.update(executionContext);
    }

    @Override
    public void close() throws ItemStreamException {
        delegate.close();
    }
}
