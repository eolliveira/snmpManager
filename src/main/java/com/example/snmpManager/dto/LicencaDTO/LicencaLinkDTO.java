package com.example.snmpManager.dto.LicencaDTO;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class LicencaLinkDTO implements Serializable {
    private Long ativoId;
    private Long licencaId;
}
