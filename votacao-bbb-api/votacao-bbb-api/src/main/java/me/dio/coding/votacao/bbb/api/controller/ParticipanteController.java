package me.dio.coding.votacao.bbb.api.controller;


import lombok.AllArgsConstructor;
import me.dio.coding.votacao.bbb.api.model.ParametroModel;
import me.dio.coding.votacao.bbb.api.model.ParticipanteModel;
import me.dio.coding.votacao.bbb.api.repository.ParticipanteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/participantes")
@AllArgsConstructor
@CrossOrigin
public class ParticipanteController {

    //NOVA FORMA DE INJEÇÃO DE DEPENDENCIA, CLICAR NO REPOSITORIO PARA INCLUIR A CLASSE ABAIXO
    private final ParticipanteRepository repository;


    @PostMapping("/salvar") //@RequestBody = recebo um parametro do tipo objeto
    public ResponseEntity<ParticipanteModel> salvar(@RequestBody ParticipanteModel participante) {
        ParticipanteModel entidade = repository.save(participante);
        return ResponseEntity.ok(entidade);
    }

    @GetMapping("/consultar" )
    public ResponseEntity<ParticipanteModel> consulta(@RequestParam String id) {
        Optional<ParticipanteModel> opt = repository.findById(id);
        if(opt.isEmpty()){
            return ResponseEntity.notFound().build(); //retorna um 404
        }

        return ResponseEntity.ok(opt.get()); // se tiver a informação retorna a consulta
    }

    @GetMapping("/todos" ) // PARA LISTAR TODOS OS PARTICIPANTES
    public ResponseEntity<List<ParticipanteModel>> todos() {
        List<ParticipanteModel> list = repository.findAll();

        return ResponseEntity.ok(list); //
    }
}