package com.example.snmpManager.mibs;

import lombok.Data;

@Data
public class ImpressoraMIB {
    private final String NOME_OID = ".1.3.6.1.2.1.1.5.0";
    private final String MODELO_OID = ".1.3.6.1.2.1.25.3.2.1.3.1";
    private final String NUMERO_SERIE_OID = ".1.3.6.1.2.1.43.5.1.1.17.1";
    private final String TEMPO_LIGADO_OID = ".1.3.6.1.2.1.1.3.0";
    private final String ENDERECO_MAC_OID = ".1.3.6.1.2.1.2.2.1.6.2";
    private final String TOTAL_IMPRESSOES_OID = ".1.3.6.1.2.1.43.10.2.1.4.1.1";
}
