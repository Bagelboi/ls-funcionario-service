package org.daniel.LS_funcionario_service.service;

import org.daniel.LS_funcionario_service.model.Grupo;
import org.daniel.LS_funcionario_service.model.GrupoFuncionario;
import org.daniel.LS_funcionario_service.repo.GrupoFuncionarioRepo;
import org.daniel.LS_funcionario_service.repo.GrupoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class GrupoService {
    @Autowired
    GrupoRepo repo;
    @Autowired
    FuncionarioService funService;
    @Autowired
    GrupoFuncionarioRepo gpFcRepo;

    public Flux<Grupo> getAll() {
        return repo.findAll();
    }

    public Mono<Grupo> get(Long id) {
        return repo.findById(id);
    }

    public Mono<Grupo> save(Grupo gp) {
        return repo.save(gp);
    }

    public Mono<Grupo> update(Long id, Grupo gp) {
        Optional<Grupo> op = get(id).blockOptional();
        if (op.isPresent()) {
            gp.setId(id);
            return save(gp);
        }
        return Mono.empty();
    }

    public Mono<?> delete (Long id) {
        return repo.deleteById(id);
    }

    public Mono<?> setFuncionario(Long gp_id, Long fun_id) {
        return get(gp_id).map( gp -> {
            if (gp != null) {
                return funService.get(fun_id).mapNotNull(fuck -> {
                    GrupoFuncionario fc = new GrupoFuncionario();
                    fc.setFunc_id(fun_id);
                    fc.setGp_id(gp_id);
                    gpFcRepo.deleteById(fun_id);
                    return gpFcRepo.save( fc );
                });
            }
            return Mono.empty();
        });
    }

}
