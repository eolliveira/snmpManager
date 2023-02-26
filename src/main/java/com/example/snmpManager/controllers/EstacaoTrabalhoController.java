package com.example.snmpManager.controllers;

import com.example.snmpManager.dto.DiscoDTO.DiscoDTO;
import com.example.snmpManager.dto.EstacaoTrabalhoDTO.WindowsDTO.EstacaoTrabalhoBasicDTO;
import com.example.snmpManager.dto.EstacaoTrabalhoDTO.WindowsDTO.EstacaoTrabalhoDTO;
import com.example.snmpManager.dto.InterfaceAtivoDTO.InterfaceDTO;
import com.example.snmpManager.dto.MotivoAtivoDTO.MovimentoDTO;
import com.example.snmpManager.objects.EstacaoTrabalhoObjects.WorkstationObject;
import com.example.snmpManager.services.EstacaoTrabalhoService.*;
import com.example.snmpManager.services.MovimentoService.FindMovesWorkstationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/workstation")
public class EstacaoTrabalhoController {
    @Autowired
    private GetDataFromWorkstationService getDataFromWorkstationService;

    @Autowired
    private FindWorkstationService findWorkstationService;

    @Autowired
    private RemoveWorkstationService removeWorkstationService;

    @Autowired
    private UpdateWorkstationService updateWorkstationService;

    @Autowired
    private NewWorkstationService newWorkstationService;

    @Autowired
    private SyncWorkstationByAssetIdService syncWorkstationByAssetIdService;

    @Autowired
    private FindInterfacesWorkstationService findInterfacesWorkstationService;

    @Autowired
    private FindDiscsWorkstationService findDiscsWorkstationService;

    @Autowired
    private FindMovesWorkstationService findMovesWorkstationService;


    //obtem dados da estação de trabalho
    @GetMapping(value = "/{ipAddress}")
    public ResponseEntity<WorkstationObject> getDataByAddress(@PathVariable String ipAddress) {
        WorkstationObject win = getDataFromWorkstationService.getWorkstationData(ipAddress);
        return ResponseEntity.ok(win);
    }


    //lista basica ,todas as estações
    @GetMapping
    public ResponseEntity<List<EstacaoTrabalhoBasicDTO>> findAll() {
        List<EstacaoTrabalhoBasicDTO> estacoes = findWorkstationService.findAll();
        return ResponseEntity.ok(estacoes);
    }


    //lista interfaces da estação
    @GetMapping(value = "/{idActive}/interfaces")
    public ResponseEntity<List<InterfaceDTO>> findAllInterfaces(@PathVariable Long idActive) {
        List<InterfaceDTO> interfaces = findInterfacesWorkstationService.findAllInterfaces(idActive);
        return ResponseEntity.ok(interfaces);
    }


    //lista discos da estação
    @GetMapping(value = "/{idActive}/hardDrives")
    public ResponseEntity<List<DiscoDTO>> findAllDiscs(@PathVariable Long idActive) {
        List<DiscoDTO> discos = findDiscsWorkstationService.findAllDiscs(idActive);
        return ResponseEntity.ok(discos);
    }


    //lista de movimentos da estação
    @GetMapping(value = "/{idActive}/moves")
    public ResponseEntity<List<MovimentoDTO>> findAllMoves(@PathVariable Long idActive) {
        List<MovimentoDTO> movimentos = findMovesWorkstationService.findAllMoves(idActive);
        return ResponseEntity.ok(movimentos);
    }


    //add nova estação de trabalho
    @PostMapping()
    public ResponseEntity<EstacaoTrabalhoDTO> insertNewWorkStation(@RequestBody EstacaoTrabalhoDTO dto) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        EstacaoTrabalhoDTO estacaoCriada = newWorkstationService.insertNewWorkStation(dto);
        return ResponseEntity.created(uri).body(estacaoCriada);
    }


    //sincroniza dados pelo id da estação de trabalho
    @PutMapping(value = "/{idActive}/synchronize")
    public void synchronize(@PathVariable Long idActive) {
        syncWorkstationByAssetIdService.synchronizeWorstation(idActive);
    }


    //atualiza estação passando o id
    @PutMapping(value = "/{idActive}/update")
    public ResponseEntity<EstacaoTrabalhoDTO> updateWorkStation(@PathVariable Long idActive, @RequestBody EstacaoTrabalhoDTO dto) {
        dto = updateWorkstationService.updateWorkStation(idActive, dto);
        return ResponseEntity.ok(dto);
    }


    @DeleteMapping(value = "/{idActive}")
    public ResponseEntity<Void> deleteWorkstation(@PathVariable Long idActive) {
        removeWorkstationService.deleteWorkstation(idActive);
        return ResponseEntity.noContent().build();
    }

}
