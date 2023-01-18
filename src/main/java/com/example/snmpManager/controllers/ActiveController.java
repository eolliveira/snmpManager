package com.example.snmpManager.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/activies")
public class ActiveController {

	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<?> listar() {
		return ResponseEntity.ok().body("lista de ativos");
	}
}