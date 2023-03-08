package com.example.snmpManager.util;

import com.example.snmpManager.dto.AtivoDTO.AtivoDTO;
import com.example.snmpManager.dto.InterfaceDTO.InterfaceDTO;
import com.example.snmpManager.entities.InterfaceEntity;
import com.example.snmpManager.exceptions.DataBaseException;
import com.example.snmpManager.repositories.InterfaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class AddressValidation {

    private final InterfaceRepository interfaceRepository;

    public static Boolean isValidIpv4(String address) {
        Pattern p = Pattern.compile("(([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])");
        return p.matcher(address).matches();
    }

    public void addressAlreadyExists(AtivoDTO dto) {
        for (InterfaceDTO i : dto.getInterfaces()) {
            if (!Objects.equals(i.getEnderecoIp(), "") && i.getEnderecoIp() != null) {
                InterfaceEntity interfaceEntity = interfaceRepository.findByEnderecoIp(i.getEnderecoIp());
                if (!(interfaceEntity == null)) {
                    throw new DataBaseException("Ativo [ " + interfaceEntity.getAtivo().getId() + " - " + interfaceEntity.getAtivo().getNome()
                            + " ], ja possui o ip [ " + i.getEnderecoIp() + " ] cadastrado");
                }
            }
        }
    }
}