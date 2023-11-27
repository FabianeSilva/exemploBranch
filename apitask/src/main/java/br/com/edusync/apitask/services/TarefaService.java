package br.com.edusync.apitask.services;

import br.com.edusync.apitask.models.Tarefa;
import br.com.edusync.apitask.repositories.TarefaRepository;
import br.com.edusync.apitask.repositories.criteria.TarefaRepositoryCustom;
import br.com.edusync.apitask.repositories.criteria.params.TarefaFilterParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private TarefaRepositoryCustom tarefaRepositoryCustom;

    public void adicionar(Tarefa tarefaQueSeraSalva) {
        tarefaRepository.save(tarefaQueSeraSalva);
    }

    public List<Tarefa> listarTudo() {
        return tarefaRepository.findAll();
    }

    public Tarefa buscarPorCodigo(Integer codigo) {
        Optional<Tarefa> optTarefa = tarefaRepository.findById(codigo);
        if (optTarefa.isEmpty()) {
            throw new RuntimeException("PRODUTO NÃO ENCONTRADO");
        }
        return optTarefa.get();
    }

    public void uptade(Integer codigo, Tarefa tarefa) {
        if (tarefaRepository.existsById(codigo)) {
            tarefaRepository.save(tarefa);
        }
    }

    public void remover(Integer codigo) {
        if (tarefaRepository.existsById(codigo)) {
            tarefaRepository.deleteById(codigo);
        }
    }

    public List<Tarefa> listarPorNome(String descricaoDaBusca) {
        return tarefaRepository.findByDescricaoContainingIgnoreCase(descricaoDaBusca);
    }

    public List<Tarefa> filtar(TarefaFilterParam params) {
        return tarefaRepositoryCustom.getFiltro(params);
    }
}
