package com.example.snmpManager.controllers;

import com.example.snmpManager.objects.PrinterObject;
import com.example.snmpManager.services.ImpressoraService.GetDataFromPrinterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/printer")
@RequiredArgsConstructor
public class ImpressoraController {

    private final GetDataFromPrinterService PrinterObject;

    //obtem dados da impressora
    @GetMapping(value = "/{ipAddress}")
    public ResponseEntity<PrinterObject> getDataByAddress(@PathVariable String ipAddress) {
        PrinterObject printer = PrinterObject.getPrinterData(ipAddress);
        return ResponseEntity.ok(printer);
    }
}
