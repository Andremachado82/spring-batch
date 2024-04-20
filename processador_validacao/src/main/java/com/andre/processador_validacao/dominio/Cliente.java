package com.andre.processador_validacao.dominio;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;

public class Cliente implements Serializable {

    @NotNull
    @Size(min = 1, max = 100)
    @Pattern(regexp = "[a-zA-Z\\s]+", message = "Nome de ser alfabético")
    private String nome;

    @NotNull
    @Range(min = 18, max = 200)
    private String idade;

    @NotNull
    @Size(min = 1, max = 50)
    @Email(message = "Email inválido")
    private String email;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nome='" + nome + "'" +
                ", sobrenome ='" +
                ", idade='" + idade + "'" +
                ", email='" + email + "'" +
                '}';
    }
}
