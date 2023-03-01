package com.example.snmpManager.controllers;

import com.example.snmpManager.dto.FinanceiroDTO.FinanceiroDTO;
import com.example.snmpManager.services.FinanceiroService.NewFinancialService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/financial")
public class FinanceiroController {
    private final NewFinancialService newFinancialService;

    @PostMapping()
    public ResponseEntity<FinanceiroDTO> insertNewFinancial(@RequestBody FinanceiroDTO dto) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        FinanceiroDTO financeiroAtivo = newFinancialService.insertNewFinancial(dto);
        return ResponseEntity.created(uri).body(financeiroAtivo);
    }

}
