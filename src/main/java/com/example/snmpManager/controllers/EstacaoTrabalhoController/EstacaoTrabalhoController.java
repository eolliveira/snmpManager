package com.example.snmpManager.controllers.EstacaoTrabalhoController;

import com.example.snmpManager.dto.EstacaoTrabalhoDTO.WindowsDTO.EstacaoTrabalhoBasicDTO;
import com.example.snmpManager.dto.EstacaoTrabalhoDTO.WindowsDTO.EstacaoTrabalhoDTO;
import com.example.snmpManager.dto.EstacaoTrabalhoDTO.WindowsDTO.EstacaoTrabalhoSynchronizeDTO;
import com.example.snmpManager.objects.EstacaoTrabalhoObjects.WindowsObjects.WindowsObject;
import com.example.snmpManager.services.EstacaoTrabalhoService.WindowsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workstation")
public class EstacaoTrabalhoController {

    @Autowired
    private WindowsService windowsService;

    @GetMapping
    public ResponseEntity<List<EstacaoTrabalhoBasicDTO>> findAll() {
        List<EstacaoTrabalhoBasicDTO> estacoes = windowsService.findAll();
        return ResponseEntity.ok(estacoes);
    }

    @GetMapping(value = "/{ipAddress}")
    public ResponseEntity<WindowsObject> getDataByAddress(@PathVariable String ipAddress) {
        WindowsObject win = windowsService.getObjectData(ipAddress);
        return ResponseEntity.ok(win);
    }

    //add nova estação de trabalho
    @PostMapping()
    public ResponseEntity<EstacaoTrabalhoDTO> insertNewWorkStation(@RequestBody EstacaoTrabalhoDTO dto) {
        EstacaoTrabalhoDTO estacaoCriada = windowsService.insertNewWorkStation(dto);
        return ResponseEntity.ok(estacaoCriada);
    }

    //atualiza estação passando o id
    @PutMapping(value = "/{idActive}/update")
    public ResponseEntity<EstacaoTrabalhoSynchronizeDTO> updateWorkStation(@PathVariable Long idActive, @RequestBody EstacaoTrabalhoSynchronizeDTO dto) {
        dto = windowsService.updateWorkStation(idActive, dto);
        return ResponseEntity.ok(dto);
    }

    @PutMapping(value = "/{idActive}/synchronize")
    public void synchronize(@PathVariable Long idActive) {
        windowsService.synchronize(idActive);
    }

}
