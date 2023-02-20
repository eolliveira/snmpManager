package com.example.snmpManager;

import com.example.snmpManager.services.SNMPTrapReciever;
import org.snmp4j.smi.UdpAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SnmpManagerApplication implements CommandLineRunner {

  @Autowired
  private SNMPTrapReciever trapReciever;

  public static void main(String[] args) {
    SpringApplication.run(SnmpManagerApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    trapReciever.listen(new UdpAddress("localhost/162"));
  }
}
