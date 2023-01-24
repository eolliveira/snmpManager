package com.example.snmpManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class SnmpManagerApplication {
	public static void main(String[] args) throws IOException {
		SpringApplication.run(SnmpManagerApplication.class, args);

//		//inicia ouvinte de trap(armadilhas) para receber dos agentes agentes
//		SNMPTrapReciever trapReciever = new SNMPTrapReciever();
//		trapReciever.listen(new UdpAddress("localhost/162"));


	}


}
