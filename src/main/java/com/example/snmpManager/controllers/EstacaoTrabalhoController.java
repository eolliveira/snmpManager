package com.example.snmpManager.controllers;

import com.example.snmpManager.dto.EstacaoTrabalhoDTO;
import com.example.snmpManager.dto.EstacaoTrabalhoUpdateDTO;
import com.example.snmpManager.objects.WindowsObject;
import com.example.snmpManager.services.EstacaoTrabalhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workstation")
public class EstacaoTrabalhoController {

    @Autowired
    private EstacaoTrabalhoService estacaoTrabalhoService;

    @GetMapping
    public ResponseEntity<List<EstacaoTrabalhoDTO>> findAll() {
        List<EstacaoTrabalhoDTO> estacoes = estacaoTrabalhoService.findAll();
        return ResponseEntity.ok(estacoes);
    }

    @GetMapping(value = "/{ipAddress}")
    public ResponseEntity<WindowsObject> getDataByAddress(@PathVariable String ipAddress) {
        WindowsObject win = estacaoTrabalhoService.getObjectData(ipAddress);
        return ResponseEntity.ok(win);
    }

    //add nova estação de trabalho
    @PostMapping()
    public ResponseEntity<EstacaoTrabalhoDTO> insertNewWorkStation(@RequestBody EstacaoTrabalhoDTO dto) {
        EstacaoTrabalhoDTO estacaoCriada = estacaoTrabalhoService.insertNewWorkStation(dto);
        return ResponseEntity.ok(estacaoCriada);
    }


    //atualiza estação passando o id
    @PutMapping(value = "/{idActive}/update")
    public ResponseEntity<EstacaoTrabalhoUpdateDTO> updateWorkStation(@PathVariable Long idActive, @RequestBody EstacaoTrabalhoUpdateDTO dto) {
        dto = estacaoTrabalhoService.updateWorkStation(idActive, dto);
        return ResponseEntity.ok(dto);
    }

    @PutMapping(value = "/{idActive}/synchronize")
    public void synchronize(@PathVariable Long idActive) {
        estacaoTrabalhoService.synchronize(idActive);
    }

}
