package br.com.edusync.apitask.repositories.criteria.impl;

import br.com.edusync.apitask.models.Tarefa;
import br.com.edusync.apitask.repositories.criteria.TarefaRepositoryCustom;
import br.com.edusync.apitask.repositories.criteria.params.TarefaFilterParam;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TarefaRepositoryCustomImpl implements TarefaRepositoryCustom {

    private EntityManager entityManager;

    public TarefaRepositoryCustomImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    @Override
    public List<Tarefa> getFiltro(TarefaFilterParam params) {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Tarefa> query = criteriaBuilder.createQuery(Tarefa.class);

        Root<Tarefa> tarefa = query.from(Tarefa.class);
        List<Predicate> predicates = new ArrayList<>();

        if ( params.getTitulo() != null ) {
            predicates.add(criteriaBuilder.like(tarefa.get("titulo"), "%" + params.getTitulo() + "%"));
        }
        if ( params.getDescricao() != null ) {
            predicates.add(criteriaBuilder.like(tarefa.get("descricao"), "%" + params.getDescricao() + "%"));
        }
        if ( params.getDataValidade() != null ) {
            predicates.add(criteriaBuilder.equal(tarefa.get("dataValidade"), params.getDataValidade()));
        }


        if (!predicates.isEmpty()) {
            query.where(predicates.stream().toArray(Predicate[]::new));
        }

        TypedQuery<Tarefa> queryResult = this.entityManager.createQuery(query);
        return queryResult.getResultList();

    }
}
