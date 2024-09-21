package org.daniel.LS_funcionario_service;

import org.daniel.LS_funcionario_service.enumerador.Clearance;
import org.daniel.LS_funcionario_service.model.Rank;

import javax.swing.text.html.Option;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class RankList {
    public static List<Rank> ranks = Arrays.asList(
      new Rank(0,0, Clearance.GRAY, "Private", "PVT"),
      new Rank(1,1, Clearance.GRAY, "Sergeant", "SGT"),
      new Rank(2, 2, Clearance.RED, "Captain", "CPT"),
      new Rank(3, 3, Clearance.BLACK, "Regiment Officer", "RGOFC"),
      new Rank(4, 4, Clearance.GREEN, "Overseer", "OVS")
    );

    public static Optional<Rank> findById(Integer id) {
        return ranks.stream().filter( rank -> rank.id().equals(id) ).findFirst();
    }
}
