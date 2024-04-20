package com.andre.aula_teste.reader;

import com.andre.aula_teste.dominio.Lancamento;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import javax.sql.DataSource;

@Configuration
public class LeituraArquivoDelimitadoReaderConfig {

    @Bean
    public JdbcCursorItemReader<Lancamento> jdbcCursorReader(
            @Qualifier("appDataSource") DataSource dataSource
            ) {
        return new JdbcCursorItemReaderBuilder<Lancamento>()
                .name("jdbcCursorReader")
                .dataSource(dataSource)
                .sql("select * from lancamento")
                .rowMapper(new BeanPropertyRowMapper<Lancamento>(Lancamento.class))
                .build();
    }
}
