package com.example.snmpManager.services.SyncService;

import com.example.snmpManager.objects.TrapObject;
import com.example.snmpManager.services.EstacaoTrabalhoService.SyncWorkstationByIpAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class SyncService {
    private final SyncWorkstationByIpAddressService syncWorkstationByIpAddressService;

    public void checkAgentSync(TrapObject trapObject) {
        if (Objects.equals(trapObject.getTipoAtivo(), "WORKSTATION")) {
            syncWorkstationByIpAddressService.synchronizeWorstation(trapObject.getIpAddress());
        }
    }


}
