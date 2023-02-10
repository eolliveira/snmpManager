package com.example.snmpManager.services;

import com.example.snmpManager.dto.UsuarioDTO.UsuarioDTO;
import com.example.snmpManager.entities.UsuarioEntity;
import com.example.snmpManager.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public UsuarioDTO insertNewUser(UsuarioDTO dto) {
        UsuarioEntity usuario = new UsuarioEntity(dto);
        usuario = usuarioRepository.save(usuario);
        return new UsuarioDTO(usuario);
    }
}
