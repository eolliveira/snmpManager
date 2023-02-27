package com.example.snmpManager.dto.AtivoDTO;

import com.example.snmpManager.dto.LicencaDTO.LicencaInsertDTO;
import com.example.snmpManager.dto.MotivoAtivoDTO.MovimentoDTO;
import com.example.snmpManager.dto.UsuarioDTO.UsuarioDTO;
import com.example.snmpManager.entities.enums.StatusAtivo;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class AtivoDTO implements Serializable {

    private Long id;
    private String nome;
    private String fabricante;
    private String modelo;
    private String descricao;
    private String numeroSerie;
    private Boolean inativo;
    private StatusAtivo status;
    private Date dtAquisicao;
    private Date dtVencimentoGarantia;
    private Date dtExpiracao;
    private Instant ultimoSincronismo;
    private BigDecimal valorCompra;
    private String fornecedor;
    private String observacao;

    private UsuarioDTO usuario;

    private List<MovimentoDTO> movimentos = new ArrayList<>();
    private List<LicencaInsertDTO> licencas = new ArrayList<>();

    public AtivoDTO() {
    }
}
