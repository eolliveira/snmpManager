package com.example.snmpManager.controllers;

import com.example.snmpManager.dto.MovimentoDTO.MovimentoDTO;
import com.example.snmpManager.dto.MovimentoDTO.MovimentoInsertDTO;
import com.example.snmpManager.services.MovimentoService.NewMovimentService;
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
@RequestMapping("/movement")
public class MovimentoController {

    private final NewMovimentService newMovimentService;

    @PostMapping()
    public ResponseEntity<MovimentoDTO> newMovement(@Valid @RequestBody MovimentoInsertDTO dto) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        MovimentoDTO movimentoDTO = newMovimentService.insertNewMoviment(dto);
        return ResponseEntity.created(uri).body(movimentoDTO);
    }

}