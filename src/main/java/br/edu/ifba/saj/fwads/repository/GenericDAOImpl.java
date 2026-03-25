package br.edu.ifba.saj.fwads.repository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.edu.ifba.saj.fwads.model.AbstractModel;

public class GenericDAOImpl<T extends AbstractModel<ID>, ID> implements GenericDAO<T, ID> {
    private Map<ID, T> bancoDeDados = new HashMap<>();
    private final Class<T> tipoClass;
    private final Class<ID> tipoIdClass;
    private final ObjectMapper mapper;
    private final File arquivo;

    public GenericDAOImpl(Class<T> tipoClass, Class<ID> tipoIdClass) {
        this.tipoClass = tipoClass;
        this.tipoIdClass = tipoIdClass;
        this.mapper = new ObjectMapper();
        this.mapper.registerModule(new JavaTimeModule());
        this.mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        this.arquivo = new File(tipoClass.getSimpleName() + ".json");
        carregarDoArquivo();
    }

    private void carregarDoArquivo() {
        if (arquivo.exists()) {
            try {
                JavaType mapType = mapper.getTypeFactory().constructMapType(HashMap.class, tipoIdClass, tipoClass);
                bancoDeDados = mapper.readValue(arquivo, mapType);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void salvarNoArquivo() {
        try {
            mapper.writeValue(arquivo, bancoDeDados);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ID salvar(T entidade) {
        ID novoId = IdGenerator.gerarNovoId(tipoIdClass);
        entidade.setId(novoId);
        entidade.setCreatedAt(LocalDateTime.now());
        bancoDeDados.put(entidade.getId(), entidade);
        salvarNoArquivo();
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
        salvarNoArquivo();
    }

    @Override
    public void deletar(ID id) {
        bancoDeDados.remove(id);
        salvarNoArquivo();
    }

    @Override
    public List<T> buscarTodos() {
        return List.copyOf(bancoDeDados.values());
    }

}