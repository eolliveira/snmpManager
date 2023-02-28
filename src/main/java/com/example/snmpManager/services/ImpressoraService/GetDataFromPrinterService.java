package com.example.snmpManager.services.ImpressoraService;

import com.example.snmpManager.exceptions.InvalidAddressExecption;
import com.example.snmpManager.mibs.ImpressoraMIB;
import com.example.snmpManager.objects.PrinterObject;
import com.example.snmpManager.services.SNMPRequestClient;
import com.example.snmpManager.util.AddressValidation;
import lombok.RequiredArgsConstructor;
import org.snmp4j.smi.OID;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetDataFromPrinterService {

    private final SNMPRequestClient snmpRequestClient;

    public PrinterObject getPrinterData(String address) {
        //TODO(Extrair este metodo)
        if (!AddressValidation.isValidIpv4(address))
            throw new InvalidAddressExecption("Endereço ip [" + address + "] inválido!");


        snmpRequestClient.start("udp:" + address + "/161", "public");

        ImpressoraMIB mib = new ImpressoraMIB();
        PrinterObject printerObj = new PrinterObject();

        String nome = snmpRequestClient.getAsString(new OID(mib.getNOME_OID()));
        String modelo = snmpRequestClient.getAsString(new OID(mib.getMODELO_OID()));
        String numeroSerie = snmpRequestClient.getAsString(new OID(mib.getNUMERO_SERIE_OID()));
        String enderecoMac = snmpRequestClient.getAsString(new OID(mib.getENDERECO_MAC_OID()));
        String tempoLigado = snmpRequestClient.getAsString(new OID(mib.getTEMPO_LIGADO_OID()));
        String totalImpressoes = snmpRequestClient.getAsString(new OID(mib.getTOTAL_IMPRESSOES_OID()));

        printerObj.setNome(nome);
        printerObj.setModelo(modelo);
        printerObj.setNumeroSerie(numeroSerie);
        printerObj.setEnderecoMac(enderecoMac);
        printerObj.setTempoLigado(tempoLigado);
        printerObj.setTotalImpressoes(totalImpressoes);

        snmpRequestClient.close();

        return printerObj;
    }
}
