package com.example.snmpManager.controllers;

import com.example.snmpManager.dto.UsuarioDTO.UsuarioDTO;
import com.example.snmpManager.services.UsuarioService.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;


    @PostMapping()
    public ResponseEntity<UsuarioDTO> newUser(@RequestBody UsuarioDTO dto) {
        UsuarioDTO usuario = usuarioService.insertNewUser(dto);
        return ResponseEntity.ok(usuario);
    }

}
