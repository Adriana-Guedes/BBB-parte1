package me.dio.codign.votacao.bbb.ms.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("votacao")
public class VotacaoModel {

    @Id
    private String id;
    private ParticipanteModel participante;
    private Date datahora;

}
