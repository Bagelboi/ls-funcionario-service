package org.daniel.LS_funcionario_service.service;

import org.daniel.LS_funcionario_service.RankList;
import org.daniel.LS_funcionario_service.model.Funcionario;
import org.daniel.LS_funcionario_service.model.Rank;
import org.daniel.LS_funcionario_service.repo.FuncionarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Comparator;
import java.util.Optional;

@Service
public class FuncionarioService {
    @Autowired
    FuncionarioRepo repo;

    Comparator<Funcionario> rankComp = new Comparator<Funcionario>() {
        @Override
        public int compare(Funcionario o1, Funcionario o2) {
            Optional<Rank> rank1 = RankList.findById(o1.getRank_id());
            Optional<Rank> rank2 =  RankList.findById(o2.getRank_id());
            if (rank1.isPresent() && rank2.isPresent())
                return Integer.compare(rank1.get().precedence(), rank2.get().precedence());
            return 0;
        }
    };

    public Flux<Funcionario> getAll() {
        return repo.findAll().sort( rankComp );
    }

    public Mono<Funcionario> get(Long id) {
        return repo.findById(id);
    }

    public Mono<Funcionario> save(Funcionario fun) {
        return repo.save(fun);
    }

    public Mono<?> update(Long id, Funcionario fun) {
        return get(id).map (op -> {
            if (op != null) {
                fun.setId(id);
                return save(fun);
            }
            return Mono.empty();
        });
    }

    public Mono<?> delete (Long id) {
        return repo.deleteById(id);
    }

}
