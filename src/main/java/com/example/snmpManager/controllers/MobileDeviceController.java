package com.example.snmpManager.controllers;

import com.example.snmpManager.dto.DispositivoMovelDTO.DispositivoMovelDTO;
import com.example.snmpManager.services.DispositivoMovelService.FindMobileDeviceService;
import com.example.snmpManager.services.DispositivoMovelService.NewMobileDeviceService;
import com.example.snmpManager.services.DispositivoMovelService.UpdateMobileDeviceService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Api(value = "API REST Dispositivos Móveis")
@CrossOrigin(origins = "*")
@RequestMapping("/mobileDevice")
public class MobileDeviceController {

    private final FindMobileDeviceService findMobileDeviceService;
    private final NewMobileDeviceService newMobileDeviceService;
    private final UpdateMobileDeviceService updateMobileDeviceService;

    @GetMapping
    public ResponseEntity<List<DispositivoMovelDTO>> findAllMobileDevice() {
        List<DispositivoMovelDTO> devices = findMobileDeviceService.findAllMobileDevices();
        return ResponseEntity.ok(devices);
    }

    @PostMapping()
    public ResponseEntity<DispositivoMovelDTO> insertNewMobileDevice(@RequestBody DispositivoMovelDTO dto) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        DispositivoMovelDTO newDevice = newMobileDeviceService.insertNewMobileDevice(dto);
        return ResponseEntity.created(uri).body(newDevice);
    }

    @PutMapping(value = "/{idActive}/update")
    public ResponseEntity<DispositivoMovelDTO> updateMobileDevice(@PathVariable Long idActive, @RequestBody DispositivoMovelDTO dto) {
        dto = updateMobileDeviceService.updateMobileDevice(idActive, dto);
        return ResponseEntity.ok(dto);
    }
}
