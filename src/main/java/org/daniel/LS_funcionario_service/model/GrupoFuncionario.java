package org.daniel.LS_funcionario_service.model;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Persistable;

@NoArgsConstructor
@Data
@Table
public class GrupoFuncionario {
    @Id
    Long func_id;
    Long gp_id;
}
