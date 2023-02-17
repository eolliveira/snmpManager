package com.example.snmpManager.controllers;

import com.example.snmpManager.dto.LicencaDTO.LicencaDTO;
import com.example.snmpManager.dto.MotivoAtivoDTO.MovimentoAtivoDTO;
import com.example.snmpManager.services.MovimentoService.MovimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movement")
public class MovimentoController {

    @Autowired
    private MovimentoService movimentoService;

    @GetMapping(value = "/{idAtivo}")
    public ResponseEntity<List<LicencaDTO>> findByActive(@PathVariable Long idAtivo) {

        return null;
    }

    @PostMapping()
    public ResponseEntity<Void> newMovement(@RequestBody MovimentoAtivoDTO dto) {
        movimentoService.insertNewMoviment(dto);
        return null;
        //TODO(Retornar Created)
    }

}
