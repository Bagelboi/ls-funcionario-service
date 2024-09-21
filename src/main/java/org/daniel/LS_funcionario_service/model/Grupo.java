package org.daniel.LS_funcionario_service.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.daniel.LS_funcionario_service.enumerador.GrupoType;
import org.springframework.data.domain.Persistable;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Table
public class Grupo implements Persistable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    GrupoType gp_type;
    String tags;

    @Override
    public boolean isNew() {
        return id == null;
    }
}
