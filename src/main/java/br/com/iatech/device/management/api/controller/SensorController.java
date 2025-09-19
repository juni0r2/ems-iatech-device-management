package br.com.iatech.device.management.api.controller;

import br.com.iatech.device.management.api.model.SensorInput;
import br.com.iatech.device.management.api.model.SensorOutput;
import br.com.iatech.device.management.common.IdGenerator;
import br.com.iatech.device.management.domain.model.Sensor;
import br.com.iatech.device.management.domain.model.SensorId;
import br.com.iatech.device.management.domain.repository.SensorRepository;
import io.hypersistence.tsid.TSID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/sensors")
@RequiredArgsConstructor
public class SensorController {

    private final SensorRepository sensorRepository;

    @GetMapping("/{sensorId}")
    public SensorOutput getOne(@PathVariable TSID sensorId) {
        Sensor sensor = sensorRepository.findById(new SensorId(sensorId))
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return convettoModel(sensor);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SensorOutput create(@RequestBody SensorInput sensorInput) {
        Sensor sensor = Sensor.builder()
                .id(new SensorId(IdGenerator.generateTSID()))
                .name(sensorInput.name())
                .ip(sensorInput.ip())
                .location(sensorInput.location())
                .protocol(sensorInput.protocol())
                .model(sensorInput.model())
                .enabled(false)
                .build();
        sensor = sensorRepository.saveAndFlush(sensor);

        return convettoModel(sensor);
    }

    private static SensorOutput convettoModel(Sensor sensor) {
        return SensorOutput.builder()
                .id(sensor.getId().getValue())
                .name(sensor.getName())
                .ip(sensor.getIp())
                .location(sensor.getLocation())
                .protocol(sensor.getProtocol())
                .model(sensor.getModel())
                .enabled(sensor.getEnabled())
                .build();
    }
}
