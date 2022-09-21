package com.example.Device.Management.design.firmware;

import com.example.Device.Management.enums.IdentifierType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/firmwares")
public class FirmwareController {
    @Autowired
    private FirmwareService service;

    @GetMapping()
    public List<FirmwareDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public FirmwareDTO findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @GetMapping("/by-name/{name}")
    public FirmwareDTO findByName(@PathVariable("name") String name) {
        return service.findByName(name);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        service.deleteById(id);
    }

    @DeleteMapping("/by-name/{name}")
    public void deleteByName(@PathVariable("name") String name) {
        service.deleteByName(name);
    }

    @PostMapping()
    public String save(@RequestBody FirmwareDTO firmwareDTO) {
        if (firmwareDTO == null || firmwareDTO.getName() == null) {
            return "Firmware does not have valid information.";
        }

        return service.save(firmwareDTO);
    }

    @PostMapping("/{id}")
    public FirmwareDTO updateById(@PathVariable("id") Long id, @RequestBody FirmwareDTO firmwareDTO) {
        if (firmwareDTO == null) {
            return null;
        }

        return service.update(id.toString(), firmwareDTO, IdentifierType.ID);
    }

    @PostMapping("/by-name/{name}")
    public FirmwareDTO updateByName(@PathVariable("name") String name, @RequestBody FirmwareDTO firmwareDTO) {
        if (firmwareDTO == null || firmwareDTO.getName() == null) {
            return null;
        }

        return service.update(name, firmwareDTO, IdentifierType.NAME);
    }
}
