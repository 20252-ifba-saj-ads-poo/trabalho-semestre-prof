package br.edu.ifba.saj.fwads.model;

import java.util.UUID;

public class Usuario extends AbstractModel<UUID> {
    private String login;
    private String senha;
    private String email;

    public Usuario(String login, String senha, String email) {
        this(login, senha);
        this.email = email;
    }

    public Usuario(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public String getEmail() {
        return email;
    }
}
