package com.example.snmpManager.controllers;

import com.example.snmpManager.dto.FinanceiroDTO.FinanceiroDTO;
import com.example.snmpManager.dto.InterfaceDTO.InterfaceDTO;
import com.example.snmpManager.dto.MovimentoDTO.MovimentoDTO;
import com.example.snmpManager.services.AtivoService.RemoveActiveService;
import com.example.snmpManager.services.FinanceiroService.FindFinancesActiveService;
import com.example.snmpManager.services.InterfaceService.FindInterfacesActiveService;
import com.example.snmpManager.services.MovimentoService.FindMovesActiveService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Api(value = "API REST Ativo")
@CrossOrigin(origins = "*")
@RequestMapping("/active")
public class AtivoController {
    private final FindMovesActiveService findMovesActiveService;
    private final FindFinancesActiveService findFinancesActiveService;
    private final RemoveActiveService removeActiveService;
    private final FindInterfacesActiveService findInterfacesActiveService;

    @GetMapping(value = "/{idActive}/interfaces")
    public ResponseEntity<List<InterfaceDTO>> findAllInterfaces(@PathVariable Long idActive) {
        List<InterfaceDTO> interfaces = findInterfacesActiveService.findAllInterfaces(idActive);
        return ResponseEntity.ok(interfaces);
    }

    @GetMapping(value = "/{idActive}/moves")
    public ResponseEntity<List<MovimentoDTO>> findAllMoves(@PathVariable Long idActive) {
        List<MovimentoDTO> movimentos = findMovesActiveService.findAllMoves(idActive);
        return ResponseEntity.ok(movimentos);
    }

    @GetMapping(value = "/{idActive}/finances")
    public ResponseEntity<List<FinanceiroDTO>> findAllFinances(@PathVariable Long idActive) {
        List<FinanceiroDTO> financas = findFinancesActiveService.findAllFinaces(idActive);
        return ResponseEntity.ok(financas);
    }

    @DeleteMapping(value = "/{idActive}")
    public ResponseEntity<Void> deleteActive(@PathVariable Long idActive) {
        removeActiveService.deleteActive(idActive);
        return ResponseEntity.noContent().build();
    }

}
