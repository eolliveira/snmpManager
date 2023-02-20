package com.example.snmpManager.starter;

import com.example.snmpManager.services.SNMPTrapReciever;
import org.snmp4j.smi.UdpAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Component
public class StartupRunner {
  @Autowired
  private SNMPTrapReciever trapReciever;

  @PostConstruct
  public void init() throws IOException {
    trapReciever.listen(new UdpAddress("localhost/162"));
  }
}
