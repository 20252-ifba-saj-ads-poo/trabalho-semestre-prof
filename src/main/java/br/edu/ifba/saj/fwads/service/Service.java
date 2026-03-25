package br.edu.ifba.saj.fwads.service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import br.edu.ifba.saj.fwads.model.AbstractModel;
import br.edu.ifba.saj.fwads.repository.GenericDAO;
import br.edu.ifba.saj.fwads.repository.GenericDAOImpl;

public class Service<T extends AbstractModel<ID>, ID> {

    private GenericDAO<T, ID> repository;

    private final Class<ID> entityClass;

    public Service(Class<ID> entityClass) {
        this.entityClass = entityClass;
        repository = new GenericDAOImpl<T, ID>(this.entityClass);
    }

    public List<T> buscarTodos() {
        return repository.buscarTodos();
    }

    public ID salvar(T entity) {
        return repository.salvar(entity);
    }

    public T buscarPorId(ID id) {
        return repository.buscarPorId(id);
    }

    public void update(T entity) {
        repository.atualizar(entity);
    }

    public void delete(ID id) {
        repository.deletar(id);
    }

    public Long count() {
        return (long) repository.buscarTodos().size();
    }

    public List<T> buscarPorParametros(Map<Function<T, ?>, Object> param) {
        return repository.buscarTodos().stream()
                .filter(entity -> {
                    for (Map.Entry<Function<T, ?>, Object> entry : param.entrySet()) {
                        Object valorNoObjeto = entry.getKey().apply(entity);
                        Object valorDesejado = entry.getValue();
                        
                        if (valorNoObjeto == null && valorDesejado != null) {
                            return false;
                        } else if (valorNoObjeto != null && !valorNoObjeto.equals(valorDesejado)) {
                            return false;
                        }
                    }
                    return true;
                })
                .toList();
    }

}