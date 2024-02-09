package com.nicode.turnero.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicode.turnero.entities.TurnsConfig;
import com.nicode.turnero.repositories.TurnsConfigRepository;

import DTO.TurnConfigDTO;

@Service
public class TurnsConfigService {

    private final TurnsConfigRepository turnsConfigRepository;

    @Autowired
    public TurnsConfigService(TurnsConfigRepository turnsConfigRepository) {
        this.turnsConfigRepository = turnsConfigRepository;
    }

    private Set<String> formatHours(List<String> hours) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("HH:mm");
        SimpleDateFormat outputFormat = new SimpleDateFormat("HH:mm");
        inputFormat.setTimeZone(TimeZone.getTimeZone("America/Argentina/Buenos_Aires"));
        outputFormat.setTimeZone(TimeZone.getTimeZone("America/Argentina/Buenos_Aires"));

        Set<String> formattedHours = new HashSet<>();

        for (String turnHour : hours) {
            try {
                Date hourAsDate = inputFormat.parse(turnHour);
                String formattedHour = outputFormat.format(hourAsDate);
                formattedHours.add(formattedHour);
                System.out.println("Zona Horaria: " + inputFormat.getTimeZone().getID() + ", Hora: " + formattedHour);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return formattedHours;
    }

    public void initializeTurnsConfig(List<TurnConfigDTO> turnConfigDTOS) {
        TurnsConfig turnsConfig = new TurnsConfig();
        Map<DayOfWeek, Set<String>> availableHoursByDay = new HashMap<>();

        for (TurnConfigDTO turnConfig : turnConfigDTOS) {
            Set<String> formattedHours = formatHours(turnConfig.getTurnHours());
            availableHoursByDay.put(DayOfWeek.valueOf(turnConfig.getDay().toUpperCase()), formattedHours);
        }

        turnsConfig.setAvailableHoursByDay(availableHoursByDay);
        turnsConfigRepository.save(turnsConfig);
    }

    public TurnsConfig getTurnsConfigs() {
        TurnsConfig configs = new TurnsConfig();
        List<TurnsConfig> turnsConfigs = turnsConfigRepository.findAll();

        if (turnsConfigs.size() > 0 && turnsConfigs.size() < 20) {
            configs = turnsConfigs.get(0);
        }

        return configs;
    }

    public void updateTurnsConfig(TurnsConfig updatedConfig) {
        TurnsConfig existingConfig = getTurnsConfigs();

        if (existingConfig != null) {
            existingConfig.setAvailableHoursByDay(updatedConfig.getAvailableHoursByDay());
            turnsConfigRepository.save(existingConfig);
        } else {
            throw new RuntimeException("No existing configuration found to update.");
        }
    }

}
