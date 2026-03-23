package br.edu.ifba.saj.fwads.servico;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import br.edu.ifba.saj.fwads.model.Autor;
import br.edu.ifba.saj.fwads.repository.GenericDAO;
import br.edu.ifba.saj.fwads.repository.GenericDAOImpl;

public class ValidarAutor {

    private static GenericDAO<Autor, UUID> repository = new GenericDAOImpl(UUID.class);

    public boolean salvar(Autor novoAutor) {
        // validar os dados obrigatórios;
        if (Objects.isNull(novoAutor.getNome()) || novoAutor.getNome().isEmpty() || novoAutor.getNome().isBlank()) {
            return false;
        }
        if (Objects.isNull(novoAutor.getEmail()) || novoAutor.getEmail().isEmpty() || novoAutor.getEmail().isBlank()) {
            return false;
        }
        if (Objects.isNull(novoAutor.getCPF()) || novoAutor.getCPF().isEmpty() || novoAutor.getCPF().isBlank()) {
            return false;
        }
        repository.salvar(novoAutor);

        return true;

    }

    public List<Autor> listarTodos() {
        return repository.buscarTodos();
    }

    public boolean atualizar(Autor entidade) {
        // validar os dados obrigatórios;
        if (Objects.isNull(entidade.getNome()) || entidade.getNome().isEmpty() || entidade.getNome().isBlank()) {
            return false;
        }
        if (Objects.isNull(entidade.getEmail()) || entidade.getEmail().isEmpty() || entidade.getEmail().isBlank()) {
            return false;
        }
        if (Objects.isNull(entidade.getCPF()) || entidade.getCPF().isEmpty() || entidade.getCPF().isBlank()) {
            return false;
        }
        repository.salvar(entidade);

        return true;

    }

    public Autor buscarPorId(UUID id){
        return repository.buscarPorId(id);
    }

    void deletar(UUID id){
         repository.deletar(id);
    }

}
