package com.andre.jdbc_cursor_reader.reader;

import com.andre.jdbc_cursor_reader.dominio.Cliente;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Configuration
public class JdbcCursorReaderConfig {

    // bean que que deve permanecer
//    @Bean
//    public JdbcCursorItemReader<Cliente> jdbcCursorReader(
//            @Qualifier("appDataSource")DataSource dataSource
//            ) {
//        return new JdbcCursorItemReaderBuilder<Cliente>()
//                .name("jdbcCursorReader")
//                .dataSource(dataSource)
//                .sql("select * from cliente")
//                .rowMapper(new BeanPropertyRowMapper<Cliente>(Cliente.class))
//                .build();
//    }

    // remover este metodo caso necessite, usado para testar
    @Bean
    public JdbcCursorItemReader<Cliente> jdbcCursorReader(
            @Qualifier("appDataSource")DataSource dataSource
    ) {
        return new JdbcCursorItemReaderBuilder<Cliente>()
                .name("jdbcCursorReader")
                .dataSource(dataSource)
                .sql("select * from cliente")
                .rowMapper(rowMapper())
                .build();
    }

    private RowMapper<Cliente> rowMapper() {
        return new RowMapper<Cliente>() {
            @Override
            public Cliente mapRow(ResultSet rs, int rowNum) throws SQLException {
                if (rs.getRow() <=11)
                    throw new SQLException(String.format("Encerrando a execução - Cliente inválido %s", rs.getString("email")));
                else return ClienteRowMapper(rs);
            }
        };
    }

    private Cliente ClienteRowMapper(ResultSet rs) throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setNome(rs.getString("nome"));
        cliente.setSobrenome(rs.getString("sobrenome"));
        cliente.setIdade(rs.getString("idade"));
        cliente.setEmail(rs.getString("email"));

        return cliente;
    }
}
