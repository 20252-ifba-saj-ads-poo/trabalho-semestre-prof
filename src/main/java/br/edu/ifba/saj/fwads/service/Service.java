package br.edu.ifba.saj.fwads.service;

import java.util.List;
import java.util.Objects;
import java.util.Arrays;
import java.util.function.Predicate;
import java.util.function.Function;

import br.edu.ifba.saj.fwads.model.AbstractModel;
import br.edu.ifba.saj.fwads.repository.GenericDAO;
import br.edu.ifba.saj.fwads.repository.GenericDAOImpl;

public class Service<T extends AbstractModel<ID>, ID> {

    private GenericDAO<T, ID> repository;

    private final Class<T> tipoClass;
    private final Class<ID> entityClass;
    private final List<Function<T, ?>> atributosObrigatorios;

    @SafeVarargs
    public Service(Class<T> tipoClass, Class<ID> entityClass, Function<T, ?>... atributosObrigatorios) {
        this.tipoClass = tipoClass;
        this.entityClass = entityClass;
        this.atributosObrigatorios = Arrays.asList(atributosObrigatorios);
        this.repository = new GenericDAOImpl<T, ID>(this.tipoClass, this.entityClass);
    }

    public List<T> buscarTodos() {
        return repository.buscarTodos();
    }

    public ID salvar(T entity) {
        if (!validar(entity)) {
            return null;
        }
        return repository.salvar(entity);
    }

    public T buscarPorId(ID id) {
        return repository.buscarPorId(id);
    }

    public void update(T entity) {
        if (!validar(entity)) {
            return;
        }
        repository.atualizar(entity);
    }

    public void delete(ID id) {
        repository.deletar(id);
    }

    public Long count() {
        return (long) repository.buscarTodos().size();
    }

    @SafeVarargs
    public final List<T> buscarPorParametros(Predicate<T>... predicados) {
        Predicate<T> filtroCombinado = Arrays.stream(predicados)
                .reduce(Predicate::and)
                .orElse(t -> true);

        return repository.buscarTodos().stream()
                .filter(filtroCombinado)
                .toList();
    }

    public boolean validar(T entity) {
        if (atributosObrigatorios == null || atributosObrigatorios.isEmpty()) {
            return true;
        }
        for (Function<T, ?> getter : atributosObrigatorios) {
            Object valor = getter.apply(entity);
            if (Objects.isNull(valor)) {
                return false;
            }
            if (valor instanceof String && ((String) valor).trim().isEmpty()) {
                return false;
            }
        }
        return true;
    }

}