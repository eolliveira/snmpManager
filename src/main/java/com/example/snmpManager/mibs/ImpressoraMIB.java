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
    private final String STATUS_OID = ".1.3.6.1.2.1.25.3.2.1.5.1";



    /* STATUS DA IMPRESSORA(HP)
        1: Other (Outros)
        2: Unknown (Desconhecido)
        3: Idle (Ociosa)
        4: Printing (Imprimindo)
        5: Warmup (Aquecendo)
        6: Stopped Printing (Impressão interrompida)
        7: Offline (Desligada)
        8: Paused (Pausada)
        9: Error (Erro)
        10: Busy (Ocupada)
        11: Not Available (Indisponível)
        12: Waiting (Aguardando)
        13: Processing Data (Processando dados)
        14: Initialization (Inicializando)
        15: Warming Up (Aquecendo)
        16: Toner Low (Toner baixo)
        17: No Toner (Sem toner)
        18: Page Punt (Falta de página)
        19: User Intervention Required (Intervenção do usuário necessária)
        20: Out of Memory (Sem memória)
        21: Door Open (Porta aberta)
        22: Server Unknown (Servidor desconhecido)
        23: Power Save (Economia de energia)
        24: Server Restarting (Servidor reiniciando)
        25: Server Required (Servidor necessário)
        26: Server Unavailable (Servidor indisponível)
    * */


}
