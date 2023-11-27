package br.com.edusync.apitask.repositories.criteria.params;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class TarefaFilterParam {

    private String titulo;
    private String descricao;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dataValidade;
}
