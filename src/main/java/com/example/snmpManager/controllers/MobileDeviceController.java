package com.example.snmpManager.controllers;

import com.example.snmpManager.dto.EstacaoTrabalhoDTO.EstacaoTrabalhoBasicDTO;
import com.example.snmpManager.dto.EstacaoTrabalhoDTO.EstacaoTrabalhoDTO;
import com.example.snmpManager.dto.FinanceiroDTO.FinanceiroDTO;
import com.example.snmpManager.dto.MovimentoDTO.MovimentoDTO;
import com.example.snmpManager.services.EstacaoTrabalhoService.*;
import com.example.snmpManager.services.FinanceiroService.FindFinancesWorkstationService;
import com.example.snmpManager.services.MovimentoService.FindMovesWorkstationService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Api(value = "API REST Dispositivos Móveis")
@CrossOrigin(origins = "*")
@RequestMapping("/mobile")
public class MobileDeviceController {

    private final FindWorkstationService findWorkstationService;
    private final RemoveWorkstationService removeWorkstationService;
    private final UpdateWorkstationService updateWorkstationService;
    private final NewWorkstationService newWorkstationService;
    private final FindMovesWorkstationService findMovesWorkstationService;
    private final FindFinancesWorkstationService findFinancesWorkstationService;

    @GetMapping
    public ResponseEntity<List<EstacaoTrabalhoBasicDTO>> findAllMobileDevice() {
        List<EstacaoTrabalhoBasicDTO> estacoes = findWorkstationService.findAll();
        return ResponseEntity.ok(estacoes);
    }

    @GetMapping(value = "/{idActive}/moves")
    public ResponseEntity<List<MovimentoDTO>> findAllMobileDeviceMovements(@PathVariable Long idActive) {
        List<MovimentoDTO> movimentos = findMovesWorkstationService.findAllMoves(idActive);
        return ResponseEntity.ok(movimentos);
    }

    @GetMapping(value = "/{idActive}/finances")
    public ResponseEntity<List<FinanceiroDTO>> findAllMobileDeviceFinances(@PathVariable Long idActive) {
        List<FinanceiroDTO> financas = findFinancesWorkstationService.findAllFinaces(idActive);
        return ResponseEntity.ok(financas);
    }

    @PostMapping()
    public ResponseEntity<EstacaoTrabalhoDTO> insertNewMobileDevice(@RequestBody EstacaoTrabalhoDTO dto) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        EstacaoTrabalhoDTO estacaoCriada = newWorkstationService.insertNewWorkStation(dto);
        return ResponseEntity.created(uri).body(estacaoCriada);
    }


    @PutMapping(value = "/{idActive}/update")
    public ResponseEntity<EstacaoTrabalhoDTO> updateMobileDevice(@PathVariable Long idActive, @RequestBody EstacaoTrabalhoDTO dto) {
        dto = updateWorkstationService.updateWorkStation(idActive, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping(value = "/{idActive}")
    public ResponseEntity<Void> deleteMobileDevice(@PathVariable Long idActive) {
        removeWorkstationService.deleteWorkstation(idActive);
        return ResponseEntity.noContent().build();
    }

}
