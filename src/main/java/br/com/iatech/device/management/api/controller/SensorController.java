package br.com.iatech.device.management.api.controller;

import br.com.iatech.device.management.api.model.SensorInput;
import br.com.iatech.device.management.common.IdGenerator;
import br.com.iatech.device.management.domain.model.Sensor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sensors")
public class SensorController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Sensor create(@RequestBody SensorInput sensorInput) {
        return Sensor.builder()
                .id(IdGenerator.generateTSID())
                .name(sensorInput.name())
                .ip(sensorInput.ip())
                .location(sensorInput.location())
                .protocol(sensorInput.protocol())
                .model(sensorInput.model())
                .enabled(false)
                .build();
    }
}
