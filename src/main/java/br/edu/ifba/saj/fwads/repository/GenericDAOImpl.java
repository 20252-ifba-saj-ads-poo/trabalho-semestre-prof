package br.edu.ifba.saj.fwads.repository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import br.edu.ifba.saj.fwads.model.AbstractModel;

public class GenericDAOImpl<T extends AbstractModel<ID>, ID> implements GenericDAO<T, ID> {
    private Map<ID, T> bancoDeDados = new HashMap<>();
    private final Class<ID> tipoIdClass;

    public GenericDAOImpl(Class<ID> tipoIdClass) {
        this.tipoIdClass = tipoIdClass;
    }

    @Override
    public ID salvar(T entidade) {
        ID novoId = (ID) UUID.randomUUID();
        entidade.setId(novoId);
        entidade.setCreatedAt(LocalDateTime.now());
        bancoDeDados.put(entidade.getId(), entidade);
        return novoId;
    }

    @Override
    public T buscarPorId(ID id) {
        return bancoDeDados.get(id);
    }

    @Override
    public void atualizar(T entidade) {
        entidade.setUpdatedAt(LocalDateTime.now());
        bancoDeDados.put(entidade.getId(), entidade);
    }

    @Override
    public void deletar(ID id) {
        bancoDeDados.remove(id);
    }

    @Override
    public List<T> buscarTodos() {
        return List.copyOf(bancoDeDados.values());
    }

}