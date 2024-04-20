package com.andre.processador_script.processador;

import com.andre.processador_script.dominio.Cliente;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.support.builder.ScriptItemProcessorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SuppressWarnings("ALL")
public class ProcessadorScriptProcessorConfig {

    // Funciona até a ver~soa 8 da jdk
    @Bean
    public ItemProcessor<Cliente, Cliente> processadorScriptProcessor() throws Exception {
        return new ScriptItemProcessorBuilder<Cliente, Cliente>()
                .language("nashorn")
                .scriptSource(
                        "var email = item .getEmail();" +
                        "var arquivoExiste = `ls | grep ${email}.txt`;" +
                        "if (!arquivoExiste) item; else null;"
                )
                .build();
    }
}
