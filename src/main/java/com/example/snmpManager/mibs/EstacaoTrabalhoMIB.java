package com.example.snmpManager.mibs;

import lombok.Data;

@Data
public class EstacaoTrabalhoMIB {
    public static final String BASE_OID = ".1.3.6.1.4.1.12345";

    private final String SO_OID = BASE_OID + ".1.1.0";
    private final String ARQUITETURA_SO_OID = BASE_OID + ".1.2.0";
    private final String FABRICANTE_OID = BASE_OID + ".1.3.0";
    private final String MODELO_OID = BASE_OID + ".1.4.0";
    private final String NUMERO_SERIE_OID = BASE_OID + ".1.5.0";
    private final String PROCESSADOR_OID = BASE_OID + ".1.6.0";
    private final String MEMORIA_RAM_OID = BASE_OID + ".2.1.0";
    private final String NOME_OID = BASE_OID + ".2.2.1.0";
    private final String DOMINIO_OID = BASE_OID + ".2.2.2.0";
    private final String USUARIO_LOGADO_OID = BASE_OID + ".2.2.5.0";
    private final String GATEWAY_OID = BASE_OID + ".2.2.3.0";
    private final String DNS_OID = BASE_OID + ".2.2.4.0";
    private final String INTERFACES_OID = BASE_OID + ".2.2.6.0";
    private final String DISCO_RIGIDO_OID = BASE_OID + ".2.2.7.0";
    private final String IMPRESSORAS_OID = BASE_OID + ".2.2.8.0";
    public final String PLACAS_VIDEO_OID = BASE_OID + ".2.3.0.0";
    public final String PROGRAMAS_OID = BASE_OID + ".2.3.1.0";
}
