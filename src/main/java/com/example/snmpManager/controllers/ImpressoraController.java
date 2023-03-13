package com.example.snmpManager.controllers;

import com.example.snmpManager.dto.ImpressoraDTO.ImpressoraDTO;
import com.example.snmpManager.dto.ImpressoraDTO.ImpressoraInsertDTO;
import com.example.snmpManager.objects.PrinterObject;
import com.example.snmpManager.services.ImpressoraService.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/printer")
@RequiredArgsConstructor
public class ImpressoraController {

    private final GetDataFromPrinterService PrinterObject;
    private final NewPrinterService newPrinterService;
    private final SyncPrinterByAssetIdService synchronizeWorstation;
    private final FindPrinterService findPrinterService;
    private final RemovePrinterService removePrinterService;
    private final UpdatePrinterService updatePrinterService;

    @GetMapping(value = "/{ipAddress}")
    public ResponseEntity<PrinterObject> getDataByAddress(@PathVariable String ipAddress) {
        PrinterObject printer = PrinterObject.getPrinterData(ipAddress);
        return ResponseEntity.ok(printer);
    }

    @GetMapping
    public ResponseEntity<List<ImpressoraInsertDTO>> findAll() {
        List<ImpressoraInsertDTO> impressoras = findPrinterService.findAll();
        return ResponseEntity.ok(impressoras);
    }

    @PostMapping()
    public ResponseEntity<ImpressoraInsertDTO> insertNewPrinter(@RequestBody ImpressoraInsertDTO dto) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        ImpressoraInsertDTO impressora = newPrinterService.insertNewPrinter(dto);
        return ResponseEntity.created(uri).body(impressora);
    }

    @PutMapping(value = "/{idActive}/synchronize")
    public void synchronize(@PathVariable Long idActive) {
        synchronizeWorstation.synchronizePrinter(idActive);
    }

    @PutMapping(value = "/{idActive}/update")
    public ResponseEntity<ImpressoraDTO> updatePrinter(@PathVariable Long idActive, @RequestBody ImpressoraDTO dto) {
        dto = updatePrinterService.updatePrinter(idActive, dto);
        return ResponseEntity.ok(dto);
    }

}
