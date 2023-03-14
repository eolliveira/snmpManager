package com.example.snmpManager.controllers;

import com.example.snmpManager.dto.DiscoDTO.DiscoDTO;
import com.example.snmpManager.dto.EstacaoTrabalhoDTO.EstacaoTrabalhoBasicDTO;
import com.example.snmpManager.dto.EstacaoTrabalhoDTO.EstacaoTrabalhoDTO;
import com.example.snmpManager.dto.EstacaoTrabalhoDTO.EstacaoTrabalhoUpdateDTO;
import com.example.snmpManager.objects.EstacaoTrabalhoObjects.WorkstationObject;
import com.example.snmpManager.services.DiscoService.FindDiscsWorkstationService;
import com.example.snmpManager.services.EstacaoTrabalhoService.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Api(value = "API REST Estação de trabaalho")
@CrossOrigin(origins = "*")
@RequestMapping("/workstation")
public class EstacaoTrabalhoController {

    private final GetDataFromWorkstationService getDataFromWorkstationService;
    private final FindWorkstationService findWorkstationService;
    private final UpdateWorkstationService updateWorkstationService;
    private final NewWorkstationService newWorkstationService;
    private final SyncWorkstationByAssetIdService syncWorkstationByAssetIdService;
    private final FindDiscsWorkstationService findDiscsWorkstationService;

    @GetMapping(value = "/{ipAddress}")
    @ApiOperation(value = "Retorna informações do agente pelo endereço Ip")
    public ResponseEntity<WorkstationObject> getDataByAddress(@PathVariable String ipAddress) {
        WorkstationObject win = getDataFromWorkstationService.getWorkstationData(ipAddress);
        return ResponseEntity.ok(win);
    }

    @GetMapping
    public ResponseEntity<List<EstacaoTrabalhoBasicDTO>> findAllWorkstation() {
        List<EstacaoTrabalhoBasicDTO> estacoes = findWorkstationService.findAll();
        return ResponseEntity.ok(estacoes);
    }

    @GetMapping(value = "/{idActive}/hardDrives")
    public ResponseEntity<List<DiscoDTO>> findAllDisksInTheAsset(@PathVariable Long idActive) {
        List<DiscoDTO> discos = findDiscsWorkstationService.findAllDiscs(idActive);
        return ResponseEntity.ok(discos);
    }

    @PostMapping()
    public ResponseEntity<EstacaoTrabalhoDTO> insertNewWorkStation(@RequestBody EstacaoTrabalhoDTO dto) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        EstacaoTrabalhoDTO estacaoCriada = newWorkstationService.insertNewWorkStation(dto);
        return ResponseEntity.created(uri).body(estacaoCriada);
    }

    @PutMapping(value = "/{idActive}/synchronize")
    public void synchronizeWorkstation(@PathVariable Long idActive) {
        syncWorkstationByAssetIdService.synchronizeWorstation(idActive);
    }

    @PutMapping(value = "/{idActive}/update")
    public ResponseEntity<EstacaoTrabalhoUpdateDTO> updateWorkStation(@PathVariable Long idActive, @RequestBody EstacaoTrabalhoUpdateDTO dto) {
        dto = updateWorkstationService.updateWorkStation(idActive, dto);
        return ResponseEntity.ok(dto);
    }

}
