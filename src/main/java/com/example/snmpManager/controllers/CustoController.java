package com.example.snmpManager.controllers;

import com.example.snmpManager.dto.CustoDTO.CustoDTO;
import com.example.snmpManager.dto.CustoDTO.CustoInsertDTO;
import com.example.snmpManager.services.CustoService.NewCostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cost")
public class CustoController {
    private final NewCostService newCostService;
    @PostMapping()
    public ResponseEntity<CustoDTO> insertNewCost(@Valid @RequestBody CustoInsertDTO dto) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        CustoDTO custoDTO = newCostService.insertNewCost(dto);
        return ResponseEntity.created(uri).body(custoDTO);
    }

}
