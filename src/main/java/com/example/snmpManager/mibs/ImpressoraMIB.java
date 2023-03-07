package com.example.snmpManager.mibs;

import lombok.Data;

@Data
public class ImpressoraMIB {
    private final String NOME_OID = ".1.3.6.1.2.1.1.5.0";
    private final String MODELO_OID = ".1.3.6.1.2.1.25.3.2.1.3.1";
    private final String NUMERO_SERIE_OID = ".1.3.6.1.2.1.43.5.1.1.17.1";
    private final String TEMPO_LIGADO_OID = ".1.3.6.1.2.1.1.3.0";
    private final String ENDERECO_IP = ".1.3.6.1.2.1.4.20.1.1.";
    private final String ENDERECO_MAC_OID = ".1.3.6.1.2.1.2.2.1.6.2";
    private final String MASCARA_SUBREDE_OID = ".1.3.6.1.2.1.4.20.1.3.";
    private final String GATEWAY_OID = ".1.3.6.1.4.1.11.2.4.3.5.13.0";
    private final String TOTAL_IMPRESSOES_OID = ".1.3.6.1.2.1.43.10.2.1.4.1.1";
    private final String DOMINIO_OID = ".1.3.6.1.4.1.11.2.4.3.5.46.0";
    private final String STATUS_OID = ".1.3.6.1.2.1.25.3.2.1.5.1";

    /* status da impressora
    *   unknown(1),
        running(2),
        warning(3),
        testing(4),
        down(5)
    */

}
