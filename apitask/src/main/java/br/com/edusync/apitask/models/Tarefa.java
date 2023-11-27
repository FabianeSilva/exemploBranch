package br.com.edusync.apitask.models;

import jakarta.persistence.*;
import lombok.Data;


import java.time.LocalDate;

@Data
@Entity(name = "tb_tarefa")
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer codigo;

    @Column(length = 150)
    private String titulo;
    private String descricao;
    private LocalDate dataValidade;
}
