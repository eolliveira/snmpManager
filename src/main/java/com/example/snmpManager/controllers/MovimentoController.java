package com.example.snmpManager.controllers;

import com.example.snmpManager.dto.LicencaDTO.LicencaDTO;
import com.example.snmpManager.dto.MotivoAtivoDTO.MovimentoDTO;
import com.example.snmpManager.dto.MotivoAtivoDTO.MovimentoInsertDTO;
import com.example.snmpManager.services.MovimentoService.NewMovimentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/movement")
public class MovimentoController {

    @Autowired
    private NewMovimentService newMovimentService;

    @GetMapping(value = "/{idAtivo}")
    public ResponseEntity<List<LicencaDTO>> findByActive(@PathVariable Long idAtivo) {

        return null;
    }

    @PostMapping()
    public ResponseEntity<MovimentoDTO> newMovement(@RequestBody MovimentoInsertDTO dto) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        MovimentoDTO movimentoDTO = newMovimentService.insertNewMoviment(dto);
        return ResponseEntity.created(uri).body(movimentoDTO);
    }

}
