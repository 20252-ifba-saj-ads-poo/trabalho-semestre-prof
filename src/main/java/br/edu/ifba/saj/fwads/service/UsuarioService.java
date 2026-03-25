package br.edu.ifba.saj.fwads.service;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.UUID;

import br.edu.ifba.saj.fwads.exception.AutenticacaoInvalidaException;
import br.edu.ifba.saj.fwads.model.Usuario;

public class UsuarioService extends Service<Usuario, UUID> {

    public UsuarioService() {
        super(UUID.class);
    }

    public Usuario validaLogin(String login, String senha) throws AutenticacaoInvalidaException {
        try {
            return buscarPorParametros(Map.of(Usuario::getLogin, login, Usuario::getSenha, senha)).getFirst();
        } catch (NoSuchElementException e) {
            throw new AutenticacaoInvalidaException(
                    "Não foi possível localizar o usuário " + login + ", ou a senha esta errada");
        }
    }

}
