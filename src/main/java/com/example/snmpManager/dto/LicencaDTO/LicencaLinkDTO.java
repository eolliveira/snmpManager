package com.example.snmpManager.dto.LicencaDTO;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
public class LicencaLinkDTO implements Serializable {
    @NotNull(message = "Campo requerido")
    private Long ativoId;
    @NotNull(message = "Campo requerido")
    private Long licencaId;
}
