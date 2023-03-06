package com.example.snmpManager.controllers;

import com.example.snmpManager.dto.EstacaoTrabalhoDTO.WindowsDTO.EstacaoTrabalhoDTO;
import com.example.snmpManager.dto.ImpressoraDTO.ImpressoraInsertDTO;
import com.example.snmpManager.objects.PrinterObject;
import com.example.snmpManager.services.ImpressoraService.GetDataFromPrinterService;
import com.example.snmpManager.services.ImpressoraService.NewPrinterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/printer")
@RequiredArgsConstructor
public class ImpressoraController {

    private final GetDataFromPrinterService PrinterObject;

    private final NewPrinterService newPrinterService;

    //obtem dados da impressora
    @GetMapping(value = "/{ipAddress}")
    public ResponseEntity<PrinterObject> getDataByAddress(@PathVariable String ipAddress) {
        PrinterObject printer = PrinterObject.getPrinterData(ipAddress);
        return ResponseEntity.ok(printer);
    }

    //add nova Impressora
    @PostMapping()
    public ResponseEntity<ImpressoraInsertDTO> insertNewPrinter(@RequestBody ImpressoraInsertDTO dto) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        ImpressoraInsertDTO impressora = newPrinterService.insertNewPrinter(dto);
        return ResponseEntity.created(uri).body(impressora);
    }
}
