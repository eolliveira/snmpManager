package com.example.snmpManager.util;

import com.example.snmpManager.dto.EstacaoTrabalhoDTO.WindowsDTO.EstacaoTrabalhoDTO;
import com.example.snmpManager.dto.InterfaceAtivoDTO.InterfaceDTO;
import com.example.snmpManager.entities.InterfaceEntity;
import com.example.snmpManager.exceptions.DataBaseException;
import com.example.snmpManager.repositories.InterfaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.regex.Pattern;
@Component
public class AddressValidation {

    @Autowired
    InterfaceRepository interfaceRepository;
    public static Boolean isValidIpv4(String address) {
        // Regex expression for validating IPv4
        String regex = "(([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])";
        Pattern p = Pattern.compile(regex);

        if (p.matcher(address).matches())
            return true;
        return false;
    }

    public void addressAlreadyExists(EstacaoTrabalhoDTO dto) {
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