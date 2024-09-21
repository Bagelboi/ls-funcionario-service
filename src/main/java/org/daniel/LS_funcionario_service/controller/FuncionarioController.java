package org.daniel.LS_funcionario_service.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.daniel.LS_funcionario_service.RankList;
import org.daniel.LS_funcionario_service.model.Funcionario;
import org.daniel.LS_funcionario_service.model.Grupo;
import org.daniel.LS_funcionario_service.service.FuncionarioService;
import org.daniel.LS_funcionario_service.service.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Optional;

@RestController
@RequestMapping("/funcionario")
@Slf4j
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private GrupoService grupoService;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping
    public Flux<Funcionario> getAllFuncionarios() {
        return funcionarioService.getAll();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Funcionario>> getFuncionarioById(@PathVariable Long id) {
        return funcionarioService.get(id)
                .map(funcionario -> ResponseEntity.ok(funcionario))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/details/{id}")
    public Mono<?> getFuncionarioDetails(@PathVariable Long id){
        return funcionarioService.get(id).flatMap( func -> {
            if (func != null) {
                try {
                HashMap<String, Object> json = new HashMap<>();
                json.put("funcionario", objectMapper.writeValueAsString(func));
                json.put("rank", objectMapper.writeValueAsString(RankList.findById(func.getRank_id())));
                grupoService.get(id).doOnSuccess( fuck -> {
                    try {
                        json.put("grupo", objectMapper.writeValueAsString(fuck));
                    } catch (JsonProcessingException e) {
                    }
                } );
                    return Mono.just(ResponseEntity.ok(objectMapper.writeValueAsString(json)));
                } catch (JsonProcessingException e) {
                    return Mono.just(ResponseEntity.notFound().build());
                }
            }
            return Mono.just(ResponseEntity.notFound().build());
        });
    }

    @PostMapping
    public Mono<ResponseEntity<Funcionario>> createFuncionario(@RequestBody Funcionario funcionario) {
        return funcionarioService.save(funcionario)
                .map(savedFuncionario -> ResponseEntity.ok(savedFuncionario));
    }

    @PutMapping("/{id}")
    public Mono<?> updateFuncionario(@PathVariable Long id, @RequestBody Funcionario funcionario) {
        return funcionarioService.update(id, funcionario)
                .map(updatedFuncionario -> ResponseEntity.ok(updatedFuncionario))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteFuncionario(@PathVariable Long id) {
        return funcionarioService.delete(id)
                .then(Mono.just(ResponseEntity.noContent().build()));
    }

}
