package com.example.Device.Management.design.hardware;

import com.example.Device.Management.enums.IdentifierType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hardwares")
public class HardwareController {
    @Autowired
    private HardwareService service;

    @GetMapping()
    public List<HardwareDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public HardwareDTO findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @GetMapping("/by-name/{name}")
    public HardwareDTO findByName(@PathVariable("name") String name) {
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
    public String save(@RequestBody HardwareDTO hardwareDTO) {
        if (hardwareDTO == null || hardwareDTO.getName() == null) {
            return "Hardware does not have valid information.";
        }

        return service.save(hardwareDTO);
    }

    @PostMapping("/{id}")
    public HardwareDTO updateById(@PathVariable("id") Long id, @RequestBody HardwareDTO hardwareDTO) {
        if (hardwareDTO == null) {
            return null;
        }

        return service.update(id.toString(), hardwareDTO, IdentifierType.ID);
    }

    @PostMapping("/by-name/{name}")
    public HardwareDTO updateByName(@PathVariable("name") String name, @RequestBody HardwareDTO hardwareDTO) {
        if (hardwareDTO == null || hardwareDTO.getName() == null) {
            return null;
        }

        return service.update(name, hardwareDTO, IdentifierType.NAME);
    }
}
