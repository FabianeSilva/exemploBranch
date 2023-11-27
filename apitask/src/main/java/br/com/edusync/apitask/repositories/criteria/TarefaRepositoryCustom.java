package br.com.edusync.apitask.repositories.criteria;


import br.com.edusync.apitask.models.Tarefa;
import br.com.edusync.apitask.repositories.criteria.params.TarefaFilterParam;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TarefaRepositoryCustom {

    List<Tarefa> getFiltro(TarefaFilterParam params);
}
