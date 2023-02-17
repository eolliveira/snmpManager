package com.example.snmpManager.controllers.EstacaoTrabalhoController;

import com.example.snmpManager.dto.EstacaoTrabalhoDTO.WindowsDTO.EstacaoTrabalhoBasicDTO;
import com.example.snmpManager.dto.EstacaoTrabalhoDTO.WindowsDTO.EstacaoTrabalhoDTO;
import com.example.snmpManager.dto.EstacaoTrabalhoDTO.WindowsDTO.EstacaoTrabalhoSynchronizeDTO;
import com.example.snmpManager.objects.EstacaoTrabalhoObjects.WorkstationObject;
import com.example.snmpManager.services.EstacaoTrabalhoService.EstacaoTrabalhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workstation")
public class EstacaoTrabalhoController {
    @Autowired
    private EstacaoTrabalhoService estacaoTrabalhoService;


    //obtem dados da estação de trabalho
    @GetMapping(value = "/{ipAddress}")
    public ResponseEntity<WorkstationObject> getDataByAddress(@PathVariable String ipAddress) {
        WorkstationObject win = estacaoTrabalhoService.getObjectData(ipAddress);
        return ResponseEntity.ok(win);
    }


    //sincroniza dados pelo id da estação de trabalho
    @PutMapping(value = "/{idActive}/synchronize")
    public void synchronize(@PathVariable Long idActive) {
        estacaoTrabalhoService.synchronize(idActive);
    }


    //lista basica ,todas as estações
    @GetMapping
    public ResponseEntity<List<EstacaoTrabalhoBasicDTO>> findAll() {
        List<EstacaoTrabalhoBasicDTO> estacoes = estacaoTrabalhoService.findAll();
        return ResponseEntity.ok(estacoes);
    }


    //add nova estação de trabalho
    @PostMapping()
    public ResponseEntity<EstacaoTrabalhoDTO> insertNewWorkStation(@RequestBody EstacaoTrabalhoDTO dto) {
        EstacaoTrabalhoDTO estacaoCriada = estacaoTrabalhoService.insertNewWorkStation(dto);
        return ResponseEntity.ok(estacaoCriada);
    }


    //atualiza estação passando o id
    @PutMapping(value = "/{idActive}/update")
    public ResponseEntity<EstacaoTrabalhoSynchronizeDTO> updateWorkStation(@PathVariable Long idActive, @RequestBody EstacaoTrabalhoSynchronizeDTO dto) {
        dto = estacaoTrabalhoService.updateWorkStation(idActive, dto);
        return ResponseEntity.ok(dto);
    }

}
