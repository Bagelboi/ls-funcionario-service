package org.daniel.LS_funcionario_service.repo;

import org.daniel.LS_funcionario_service.model.GrupoFuncionario;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
@Repository
public interface GrupoFuncionarioRepo extends R2dbcRepository<GrupoFuncionario, Long> {
    default Flux<GrupoFuncionario> getByGrupo(Long id) {
        return findAll().filter( cond -> cond.getGp_id().equals( id ) );
    }
}
