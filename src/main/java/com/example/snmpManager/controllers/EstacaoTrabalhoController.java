package com.example.snmpManager.controllers;

import com.example.snmpManager.dto.DiscoDTO.DiscoDTO;
import com.example.snmpManager.dto.EstacaoTrabalhoDTO.EstacaoTrabalhoBasicDTO;
import com.example.snmpManager.dto.EstacaoTrabalhoDTO.EstacaoTrabalhoDTO;
import com.example.snmpManager.dto.FinanceiroDTO.FinanceiroDTO;
import com.example.snmpManager.dto.InterfaceDTO.InterfaceDTO;
import com.example.snmpManager.dto.MovimentoDTO.MovimentoDTO;
import com.example.snmpManager.objects.EstacaoTrabalhoObjects.WorkstationObject;
import com.example.snmpManager.services.DiscoService.FindDiscsWorkstationService;
import com.example.snmpManager.services.EstacaoTrabalhoService.*;
import com.example.snmpManager.services.FinanceiroService.FindFinancesWorkstationService;
import com.example.snmpManager.services.InterfaceService.FindInterfacesWorkstationService;
import com.example.snmpManager.services.MovimentoService.FindMovesWorkstationService;
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
    private final RemoveWorkstationService removeWorkstationService;
    private final UpdateWorkstationService updateWorkstationService;
    private final NewWorkstationService newWorkstationService;
    private final SyncWorkstationByAssetIdService syncWorkstationByAssetIdService;
    private final FindInterfacesWorkstationService findInterfacesWorkstationService;
    private final FindDiscsWorkstationService findDiscsWorkstationService;
    private final FindMovesWorkstationService findMovesWorkstationService;
    private final FindFinancesWorkstationService findFinancesWorkstationService;


    @GetMapping(value = "/{ipAddress}")
    @ApiOperation(value="Retorna informações do agente pelo endereço Ip")
    public ResponseEntity<WorkstationObject> getDataByAddress(@PathVariable String ipAddress) {
        WorkstationObject win = getDataFromWorkstationService.getWorkstationData(ipAddress);
        return ResponseEntity.ok(win);
    }

    @GetMapping
    public ResponseEntity<List<EstacaoTrabalhoBasicDTO>> findAll() {
        List<EstacaoTrabalhoBasicDTO> estacoes = findWorkstationService.findAll();
        return ResponseEntity.ok(estacoes);
    }

    @GetMapping(value = "/{idActive}/interfaces")
    public ResponseEntity<List<InterfaceDTO>> findAllInterfaces(@PathVariable Long idActive) {
        List<InterfaceDTO> interfaces = findInterfacesWorkstationService.findAllInterfaces(idActive);
        return ResponseEntity.ok(interfaces);
    }

    @GetMapping(value = "/{idActive}/hardDrives")
    public ResponseEntity<List<DiscoDTO>> findAllDiscs(@PathVariable Long idActive) {
        List<DiscoDTO> discos = findDiscsWorkstationService.findAllDiscs(idActive);
        return ResponseEntity.ok(discos);
    }

    @GetMapping(value = "/{idActive}/moves")
    public ResponseEntity<List<MovimentoDTO>> findAllMoves(@PathVariable Long idActive) {
        List<MovimentoDTO> movimentos = findMovesWorkstationService.findAllMoves(idActive);
        return ResponseEntity.ok(movimentos);
    }

    @GetMapping(value = "/{idActive}/finances")
    public ResponseEntity<List<FinanceiroDTO>> findAllFinances(@PathVariable Long idActive) {
        List<FinanceiroDTO> financas = findFinancesWorkstationService.findAllFinaces(idActive);
        return ResponseEntity.ok(financas);
    }

    @PostMapping()
    public ResponseEntity<EstacaoTrabalhoDTO> insertNewWorkStation(@RequestBody EstacaoTrabalhoDTO dto) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        EstacaoTrabalhoDTO estacaoCriada = newWorkstationService.insertNewWorkStation(dto);
        return ResponseEntity.created(uri).body(estacaoCriada);
    }

    @PutMapping(value = "/{idActive}/synchronize")
    public void synchronize(@PathVariable Long idActive) {
        syncWorkstationByAssetIdService.synchronizeWorstation(idActive);
    }

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
