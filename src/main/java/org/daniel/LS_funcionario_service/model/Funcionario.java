package org.daniel.LS_funcionario_service.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Persistable;

import java.sql.Timestamp;
import java.util.HashMap;

@NoArgsConstructor
@Data
@Table
public class Funcionario implements Persistable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    Integer rank_id;
    Integer age;
    String homeplanet;

    @Override
    public boolean isNew() {
        return id == null;
    }
}
