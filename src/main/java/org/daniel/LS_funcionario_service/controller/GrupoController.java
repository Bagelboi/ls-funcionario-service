package org.daniel.LS_funcionario_service.controller;

import org.daniel.LS_funcionario_service.model.Grupo;
import org.daniel.LS_funcionario_service.model.GrupoFuncionario;
import org.daniel.LS_funcionario_service.service.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/grupo")
public class GrupoController {

    @Autowired
    private GrupoService grupoService;

    @GetMapping
    public Flux<Grupo> getAllGrupos() {
        return grupoService.getAll();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Grupo>> getGrupoById(@PathVariable Long id) {
        return grupoService.get(id)
                .map(grupo -> ResponseEntity.ok(grupo))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ResponseEntity<Grupo>> createGrupo(@RequestBody Grupo grupo) {
        return grupoService.save(grupo)
                .map(savedGrupo -> ResponseEntity.ok(savedGrupo));
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Grupo>> updateGrupo(@PathVariable Long id, @RequestBody Grupo grupo) {
        return grupoService.update(id, grupo)
                .map(updatedGrupo -> ResponseEntity.ok(updatedGrupo))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteGrupo(@PathVariable Long id) {
        return grupoService.delete(id)
                .then(Mono.just(ResponseEntity.noContent().build()));
    }

    @PutMapping("/{gp_id}/func/{fun_id}")
    public Mono<?> setFuncionario(@PathVariable Long gp_id, @PathVariable Long fun_id) {
        return grupoService.setFuncionario(gp_id, fun_id)
                .map(grupo -> ResponseEntity.ok(grupo))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

}
