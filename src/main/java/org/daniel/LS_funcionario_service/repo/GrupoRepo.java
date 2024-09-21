package org.daniel.LS_funcionario_service.repo;

import org.daniel.LS_funcionario_service.model.Funcionario;
import org.daniel.LS_funcionario_service.model.Grupo;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrupoRepo extends R2dbcRepository<Grupo, Long> {
}
