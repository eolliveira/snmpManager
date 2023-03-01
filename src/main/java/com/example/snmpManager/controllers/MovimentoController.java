package com.example.snmpManager.controllers;

import com.example.snmpManager.dto.LicencaDTO.LicencaInsertDTO;
import com.example.snmpManager.dto.MotivoAtivoDTO.MovimentoDTO;
import com.example.snmpManager.dto.MotivoAtivoDTO.MovimentoInsertDTO;
import com.example.snmpManager.services.MovimentoService.NewMovimentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movement")
public class MovimentoController {

    private final NewMovimentService newMovimentService;

    @GetMapping(value = "/{idAtivo}")
    public ResponseEntity<List<LicencaInsertDTO>> findByActive(@PathVariable Long idAtivo) {
        return null;
    }

    @PostMapping()
    public ResponseEntity<MovimentoDTO> newMovement(@RequestBody MovimentoInsertDTO dto) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        MovimentoDTO movimentoDTO = newMovimentService.insertNewMoviment(dto);
        return ResponseEntity.created(uri).body(movimentoDTO);
    }

}