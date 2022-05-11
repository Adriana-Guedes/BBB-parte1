package me.dio.codign.votacao.bbb.ms.repository;

import me.dio.codign.votacao.bbb.ms.model.VotacaoModel;
import org.springframework.data.mongodb.repository.MongoRepository;



public interface VotacaoRepository extends MongoRepository<VotacaoModel, String> {
}
