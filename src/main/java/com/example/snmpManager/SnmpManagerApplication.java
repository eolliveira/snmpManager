package com.example.snmpManager;

import com.example.snmpManager.services.SNMPTrapReciever;
import org.snmp4j.smi.UdpAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class SnmpManagerApplication implements CommandLineRunner {

  @Autowired
  private SNMPTrapReciever trapReciever;

  public static void main(String[] args) {
    SpringApplication.run(SnmpManagerApplication.class, args);
  }

  @Override
  public void run(String... args) {
    try {
      trapReciever.listen(new UdpAddress("localhost/162"));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
