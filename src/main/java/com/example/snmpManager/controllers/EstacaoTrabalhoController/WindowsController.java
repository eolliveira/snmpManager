package com.example.snmpManager.controllers.EstacaoTrabalhoController;

import com.example.snmpManager.dto.EstacaoTrabalhoDTO.WindowsDTO.WindowsBasicDTO;
import com.example.snmpManager.dto.EstacaoTrabalhoDTO.WindowsDTO.WindowsDTO;
import com.example.snmpManager.dto.EstacaoTrabalhoDTO.WindowsDTO.WindowsSynchronizeDTO;
import com.example.snmpManager.objects.EstacaoTrabalhoObjects.WindowsObjects.WindowsObject;
import com.example.snmpManager.services.EstacaoTrabalhoService.WindowsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workstation")
public class WindowsController {

    @Autowired
    private WindowsService windowsService;

    @GetMapping
    public ResponseEntity<List<WindowsBasicDTO>> findAll() {
        List<WindowsBasicDTO> estacoes = windowsService.findAll();
        return ResponseEntity.ok(estacoes);
    }

    @GetMapping(value = "/{ipAddress}")
    public ResponseEntity<WindowsObject> getDataByAddress(@PathVariable String ipAddress) {
        WindowsObject win = windowsService.getObjectData(ipAddress);
        return ResponseEntity.ok(win);
    }

    //add nova estação de trabalho
    @PostMapping()
    public ResponseEntity<WindowsDTO> insertNewWorkStation(@RequestBody WindowsDTO dto) {
        WindowsDTO estacaoCriada = windowsService.insertNewWorkStation(dto);
        return ResponseEntity.ok(estacaoCriada);
    }

    //atualiza estação passando o id
    @PutMapping(value = "/{idActive}/update")
    public ResponseEntity<WindowsSynchronizeDTO> updateWorkStation(@PathVariable Long idActive, @RequestBody WindowsSynchronizeDTO dto) {
        dto = windowsService.updateWorkStation(idActive, dto);
        return ResponseEntity.ok(dto);
    }

    @PutMapping(value = "/{idActive}/synchronize")
    public void synchronize(@PathVariable Long idActive) {
        windowsService.synchronize(idActive);
    }

}
