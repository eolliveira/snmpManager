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
        if (!AddressValidation.isValidIpv4(address))
            throw new InvalidAddressExecption("Endereço ip [" + address + "] inválido!");

        snmpRequestClient.start(address,"public");

        ImpressoraMIB mib = new ImpressoraMIB();
        PrinterObject printerObj = new PrinterObject();

        String nome = snmpRequestClient.getAsString(new OID(mib.getNOME_OID()));
        String modelo = snmpRequestClient.getAsString(new OID(mib.getMODELO_OID()));
        String numeroSerie = snmpRequestClient.getAsString(new OID(mib.getNUMERO_SERIE_OID()));
        String enderecoIp = snmpRequestClient.getAsString(new OID(mib.getENDERECO_IP() + address));
        String mascaraSubRede = snmpRequestClient.getAsString(new OID(mib.getMASCARA_SUBREDE_OID() + address));
        String enderecoMac = snmpRequestClient.getAsString(new OID(mib.getENDERECO_MAC_OID()));
        String gateway = snmpRequestClient.getAsString(new OID(mib.getGATEWAY_OID()));
        String tempoLigado = snmpRequestClient.getAsString(new OID(mib.getTEMPO_LIGADO_OID()));
        String totalImpressoes = snmpRequestClient.getAsString(new OID(mib.getTOTAL_IMPRESSOES_OID()));
        String status = snmpRequestClient.getAsString(new OID(mib.getSTATUS_OID()));

        printerObj.setNome(nome);
        printerObj.setModelo(modelo);
        printerObj.setNumeroSerie(numeroSerie);
        printerObj.setEnderecoIp(enderecoIp);
        printerObj.setMascaraSubRede(mascaraSubRede);
        printerObj.setEnderecoMac(enderecoMac);
        printerObj.setGateway(gateway);
        printerObj.setTempoLigado(tempoLigado);
        printerObj.setTotalImpressoes(totalImpressoes);
        printerObj.setStatus(status);

        snmpRequestClient.close();

        return printerObj;
    }
}
