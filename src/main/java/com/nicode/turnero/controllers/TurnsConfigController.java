package com.nicode.turnero.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nicode.turnero.entities.TurnsConfig;
import com.nicode.turnero.services.TurnsConfigService;

import DTO.TurnConfigDTO;

@RestController
@RequestMapping("/api/turnsconfig")
public class TurnsConfigController {

    @Autowired
    private TurnsConfigService turnsConfigService;

    @PostMapping("/initialize")
    public ResponseEntity<String> initializeTurnsConfig(@RequestBody List<TurnConfigDTO> turnConfigDTOs) {
        turnsConfigService.initializeTurnsConfig(turnConfigDTOs);
        return ResponseEntity.ok("Turns config initialized successfully");
    }

    @GetMapping("/configs")
    public ResponseEntity<TurnsConfig> getAllTurnsConfigs() {
        TurnsConfig configs = turnsConfigService.getTurnsConfigs();
        return ResponseEntity.ok(configs);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateTurnsConfig(@RequestBody TurnsConfig updatedConfig) {
        turnsConfigService.updateTurnsConfig(updatedConfig);
        return ResponseEntity.ok("Turns config updated successfully");
    }

}
