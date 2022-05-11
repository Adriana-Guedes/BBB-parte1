package me.dio.coding.votacao.bbb.api.service;


import lombok.AllArgsConstructor;
import me.dio.coding.votacao.bbb.api.model.ParticipanteModel;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VotacaoService {

    private static final String TOPICO_VOTACAO = "votacao"; //CRIAÇÃO DE UMA FILA PARA SOMAR, SEM GUARDAR NO BANCO DE DADOS, (PROGRAMAÇÃO ORIENTADA A EVENTOS)


    private final KafkaTemplate<Object, Object> template;

    public void adicionarEvento(ParticipanteModel participante) {
     template.send(TOPICO_VOTACAO, participante);

    }
}
