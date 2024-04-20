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
                        "print(email);" +
                        "var filePath = \"files/\" + email + \".txt\";" +
                        "var path = java.nio.file.Paths.get(filePath);" +
                        "var arquivoExiste = java.nio.file.Files.exists(path);" +
                        "if (!arquivoExiste) item; else null;"
                )
                .build();
    }
}
