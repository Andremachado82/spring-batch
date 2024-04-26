package com.andre.envio_promocoes_clientes.dominio;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cliente {

    private Integer id;
    private String nome;
    private String email;
}
