package br.com.edusync.apitask.controllers;

import br.com.edusync.apitask.models.Tarefa;
import br.com.edusync.apitask.repositories.criteria.params.TarefaFilterParam;
import br.com.edusync.apitask.services.TarefaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/tarefas")
@RestController
public class TarefasController {


    @Autowired
    private TarefaService service;

    @PostMapping(value = "/nova")
    @Operation(summary = "NOVA TAREFA", description = "ADICIONA UMA NOVA TAREFA")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK - TAREFA ENCONTRADA"),
            @ApiResponse(responseCode = "404", description = "ERRO - TAREFA NÃO LOCALIZADA"),
            @ApiResponse(responseCode = "500", description = "ERRO INESPERADO")
    })
    public ResponseEntity novaTarefa(@RequestBody Tarefa tarefa){
        service.adicionar(tarefa);
        return new ResponseEntity(tarefa, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "LISTA TODAS ASS TAREFAS", description = "API QUE LISTA TODAS AS TAREFAS , SEM APLICAR NENHUM FILTRO")
    public ResponseEntity listarTodas() {
        return new ResponseEntity(service.listarTudo(), HttpStatus.OK);
    }

    @GetMapping(value = "/{codigo}")
    @Operation(summary = "OBTER TAREFA", description = "DADO UM CODIGO DA TAREFA, RECUPERA AS SUAS INFORMAÇÕES")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK - TAREFA ENCONTRADA"),
            @ApiResponse(responseCode = "404", description = "ERRO - TAREFA NÃO LOCALIZADA"),
            @ApiResponse(responseCode = "500", description = "ERRO INESPERADO")
    })
    public ResponseEntity listarPorCodigo(@PathVariable Integer codigo) {
        try {
            return new ResponseEntity(service.buscarPorCodigo(codigo), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/{codigo}")
    @Operation(summary = "ALTERAR", description = "ALTERA DADOS DA TAREFA")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK - TAREFA ENCONTRADA"),
            @ApiResponse(responseCode = "404", description = "ERRO - TAREFA NÃO LOCALIZADA"),
            @ApiResponse(responseCode = "500", description = "ERRO INESPERADO")
    })
    public ResponseEntity alterar(@PathVariable Integer codigo, @RequestBody Tarefa tarefa) {
        service.uptade(codigo, tarefa);
        return new ResponseEntity(tarefa, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{codigo}")
    @Operation(summary = "REMOVER", description = "DELETA A TAREFA SELECIONADA")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK - TAREFA ENCONTRADA"),
            @ApiResponse(responseCode = "404", description = "ERRO - TAREFA NÃO LOCALIZADA"),
            @ApiResponse(responseCode = "500", description = "ERRO INESPERADO")
    })
    public ResponseEntity remover(@PathVariable Integer codigo) {
        service.remover(codigo);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "/nome")
    @Operation(summary = "LISTA POR NOME")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK - TAREFA ENCONTRADA"),
            @ApiResponse(responseCode = "404", description = "ERRO - TAREFA NÃO ENCONTRADA"),
            @ApiResponse(responseCode = "500", description = "ERRO INESPERADO")
    })
    public ResponseEntity listarPorNome(@RequestParam(defaultValue = "") String busca) {
        return new ResponseEntity(service.listarPorNome(busca), HttpStatus.OK);
    }

    @GetMapping(value = "/filtro")
    public ResponseEntity filtrar(TarefaFilterParam params) {
        return new ResponseEntity(service.filtar(params), HttpStatus.OK);
    }


}
