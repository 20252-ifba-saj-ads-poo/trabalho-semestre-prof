package br.edu.ifba.saj.fwads.servico;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import br.edu.ifba.saj.fwads.exception.AutenticacaoInvalidaException;
import br.edu.ifba.saj.fwads.model.Usuario;
import br.edu.ifba.saj.fwads.repository.GenericDAO;
import br.edu.ifba.saj.fwads.repository.GenericDAOImpl;

public class ValidarUsuario {

    private static GenericDAO<Usuario, UUID> repository = new GenericDAOImpl(UUID.class);

    public boolean salvar(Usuario entidade) {
        // validar os dados obrigatórios;
        if (Objects.isNull(entidade.getLogin()) || entidade.getLogin().isEmpty() || entidade.getLogin().isBlank()) {
            return false;
        }
        if (Objects.isNull(entidade.getSenha()) || entidade.getSenha().isEmpty() || entidade.getSenha().isBlank()) {
            return false;
        }

        repository.salvar(entidade);

        return true;
    }

    public List<Usuario> listarTodos() {
        return repository.buscarTodos();
    }

    public boolean atualizar(Usuario entidade) {
        // validar os dados obrigatórios;
        // validar os dados obrigatórios;
        if (Objects.isNull(entidade.getLogin()) || entidade.getLogin().isEmpty() || entidade.getLogin().isBlank()) {
            return false;
        }
        if (Objects.isNull(entidade.getSenha()) || entidade.getSenha().isEmpty() || entidade.getSenha().isBlank()) {
            return false;
        }
        repository.salvar(entidade);

        return true;

    }

    public Usuario buscarPorId(UUID id) {
        return repository.buscarPorId(id);
    }

    public void deletar(UUID id) {
        repository.deletar(id);
    }

    public void logar(Usuario u) throws AutenticacaoInvalidaException {
        for (Usuario usuario : listarTodos()) {
            if (usuario.getLogin().equals(u.getLogin()) &&
                    usuario.getSenha().equals(u.getSenha())) {
                return;
            }
        }
        throw new AutenticacaoInvalidaException("usuario ou senha inválidos");
    }

    public static void main(String[] args) {
        ValidarUsuario validarUsuario = new ValidarUsuario();
        validarUsuario.salvar(new Usuario("leandro", "leandro"));

        try {
            validarUsuario.logar(new Usuario("1leandro", "leandro"));
            System.out.print("Logado com sucesso");
        } catch (AutenticacaoInvalidaException e) {
            System.out.print(e.getMessage());
        }

    }

}
