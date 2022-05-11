package me.dio.codign.votacao.bbb.ms.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.dio.codign.votacao.bbb.ms.model.ParticipanteModel;
import me.dio.codign.votacao.bbb.ms.model.VotacaoModel;
import me.dio.codign.votacao.bbb.ms.repository.VotacaoRepository;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j //varificar os logs
@AllArgsConstructor
public class VotacaoService {


    private final VotacaoRepository repository;


        //CONSUMIDOR DE FILA

    //a fila que esta lendo, groupId é para varios microserviços consumindo ao mesmo tempo ( podendo em exemplo, registrar em varias aplicações
    @KafkaListener(topics = "votacao", groupId = "MicroServicoVotacao")
    private void executar(ConsumerRecord<String, String> registro) {


        //recebendo o voto
        String participanteStr = registro.value();
        log.info("Voto Recebido={}", participanteStr);

        ObjectMapper mapper = new ObjectMapper();
        ParticipanteModel participante = null;


        //CONVERSÃO

        try {
            participante = mapper.readValue(participanteStr, ParticipanteModel.class);
        } catch (JsonProcessingException ex) {
           log.error("Falha ao converter voto [{}]", participanteStr, ex );

           return;
        }



        VotacaoModel votacao = new VotacaoModel( null, participante, new Date());
        VotacaoModel entity = repository.save(votacao);

        log.info("voto registrado com sucesso [id={}, dataHora={}]", entity.getId(), entity.getDatahora());


    }

}
