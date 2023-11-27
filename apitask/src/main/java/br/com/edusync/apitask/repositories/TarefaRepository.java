package br.com.edusync.apitask.repositories;

import br.com.edusync.apitask.models.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TarefaRepository extends JpaRepository<Tarefa, Integer> {

    public List<Tarefa> findByDescricaoContainingIgnoreCase(String descricao);
    public Tarefa findByDescricaoAndDataValidade(String descricao, LocalDate dataValidade);


}
