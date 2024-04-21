package com.andre.migracao_dados.dominio;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.logging.log4j.util.Strings;

import java.util.Date;

@Getter
@Setter
@ToString
public class Pessoa {

    private Integer id;
    private String nome;
    private String email;
    private Date dataNascimento;
    private Integer idade;

    public boolean isValida() {
        return !Strings.isBlank(nome) && !Strings.isBlank(email) && dataNascimento != null;
    }
}
