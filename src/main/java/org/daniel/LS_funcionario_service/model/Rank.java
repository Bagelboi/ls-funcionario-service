package org.daniel.LS_funcionario_service.model;

import org.daniel.LS_funcionario_service.enumerador.Clearance;

public record Rank(Integer id, Integer precedence, Clearance clearance, String name, String name_short){}
