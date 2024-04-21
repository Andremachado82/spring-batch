package com.andre.contas_bancarias.processor;

import com.andre.contas_bancarias.dominio.Conta;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.batch.item.support.CompositeItemWriter;
import org.springframework.batch.item.support.builder.ClassifierCompositeItemWriterBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.classify.Classifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClassifierContaWriterConfig {

    @Bean
    public ClassifierCompositeItemWriter<Conta> classifierContaWriter(
            @Qualifier("clienteInvalidoWriter") FlatFileItemWriter<Conta> clienteInvalidoItemWriter,
            CompositeItemWriter<Conta> clienteValidoWriter
    ) {
        return new ClassifierCompositeItemWriterBuilder<Conta>()
                .classifier(classifier(clienteInvalidoItemWriter, clienteValidoWriter))
                .build();
    }

    private Classifier<Conta, ItemWriter<? super Conta>> classifier(FlatFileItemWriter<Conta> clienteInvalidoItemWriter, CompositeItemWriter<Conta> clienteValidoWriter) {
        return new Classifier<Conta, ItemWriter<? super Conta>>() {
            @Override
            public ItemWriter<? super Conta> classify(Conta conta) {
                if (conta.getTipo() != null)
                    return clienteValidoWriter;
                else return clienteInvalidoItemWriter;
            }
        };
    }
}
