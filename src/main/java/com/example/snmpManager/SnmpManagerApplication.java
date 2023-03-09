package com.example.snmpManager;

import com.example.snmpManager.services.SnmpService.SNMPTrapReciever;
import lombok.RequiredArgsConstructor;
import org.snmp4j.smi.UdpAddress;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.IOException;

@SpringBootApplication
@RequiredArgsConstructor
public class SnmpManagerApplication implements CommandLineRunner {

    private final SNMPTrapReciever trapReciever;

    public static void main(String[] args) {
        SpringApplication.run(SnmpManagerApplication.class, args);
        //TODO(CRUD NOBREAK)
        //TODO(CRUD MOBILE)
        //TODO(INTEGRAR SWAGGER)
        //TODO(EXTRAIR VARIAVEIS DE AMBIENTE)
    }

    @Override
    public void run(String... args) {
        try {
            trapReciever.listen(new UdpAddress("0.0.0.0/162"));
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
