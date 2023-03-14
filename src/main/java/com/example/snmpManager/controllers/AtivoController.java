package com.example.snmpManager.controllers;

import com.example.snmpManager.dto.CustoDTO.CustoDTO;
import com.example.snmpManager.dto.InterfaceDTO.InterfaceDTO;
import com.example.snmpManager.dto.LicencaDTO.LicencaDTO;
import com.example.snmpManager.dto.MovimentoDTO.MovimentoDTO;
import com.example.snmpManager.services.AtivoService.RemoveActiveService;
import com.example.snmpManager.services.CustoService.FindTheAssetCostsService;
import com.example.snmpManager.services.InterfaceService.FindInterfacesActiveService;
import com.example.snmpManager.services.LicencaService.FindTheAssetLicencesService;
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
    private final FindTheAssetCostsService findTheAssetCostsService;
    private final RemoveActiveService removeActiveService;
    private final FindInterfacesActiveService findInterfacesActiveService;
    private final FindTheAssetLicencesService findTheAssetLicencesService;

    @GetMapping(value = "/{idActive}/interfaces")
    public ResponseEntity<List<InterfaceDTO>> findAllAssetInterfaces(@PathVariable Long idActive) {
        List<InterfaceDTO> interfaces = findInterfacesActiveService.findAllInterfaces(idActive);
        return ResponseEntity.ok(interfaces);
    }

    @GetMapping(value = "/{idActive}/moves")
    public ResponseEntity<List<MovimentoDTO>> findAllMovementsOfTheAsset(@PathVariable Long idActive) {
        List<MovimentoDTO> movimentos = findMovesActiveService.findAllMoves(idActive);
        return ResponseEntity.ok(movimentos);
    }

    @GetMapping(value = "/{idActive}/costs")
    public ResponseEntity<List<CustoDTO>> findAllAssetCosts(@PathVariable Long idActive) {
        List<CustoDTO> custos = findTheAssetCostsService.findAllCosts(idActive);
        return ResponseEntity.ok(custos);
    }

    @GetMapping(value = "/{idActive}/licenses")
    public ResponseEntity<List<LicencaDTO>> findAllAssetLicenses(@PathVariable Long idActive) {
        List<LicencaDTO> licencas = findTheAssetLicencesService.findAllLicenses(idActive);
        return ResponseEntity.ok(licencas);
    }

    @DeleteMapping(value = "/{idActive}")
    public ResponseEntity<Void> deleteActive(@PathVariable Long idActive) {
        removeActiveService.deleteActive(idActive);
        return ResponseEntity.noContent().build();
    }

}
