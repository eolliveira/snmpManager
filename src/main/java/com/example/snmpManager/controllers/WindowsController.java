package com.example.snmpManager.controllers;

import com.example.snmpManager.objects.WindowsObject;
import com.example.snmpManager.services.WindowsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/win")
public class  WindowsController {

    @Autowired
    private WindowsService windowsService;

    @GetMapping(value = "/{ipAddress}")
    public ResponseEntity<WindowsObject> findAll(@PathVariable String ipAddress) throws IOException {
        WindowsObject win = windowsService.getInformation(ipAddress);
        return ResponseEntity.ok(win);
    }

}
