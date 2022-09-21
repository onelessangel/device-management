package com.example.Device.Management.design.devicetype;

import com.example.Device.Management.enums.IdentifierType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/device-types")
public class DeviceTypeController {
    @Autowired
    private DeviceTypeService service;

    @GetMapping()
    public List<DeviceTypeDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public DeviceTypeDTO findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @GetMapping("/by-name/{name}")
    public DeviceTypeDTO findByName(@PathVariable("name") String name) {
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
    public String save(@RequestBody DeviceTypeDTO deviceTypeDTO) {
        if (deviceTypeDTO == null || deviceTypeDTO.getName() == null) {
            return "Device Type does not have valid information.";
        }

        return service.save(deviceTypeDTO);
    }

    @PostMapping("/{id}")
    public DeviceTypeDTO updateById(@PathVariable("id") Long id, @RequestBody DeviceTypeDTO deviceTypeDTO) {
        if (deviceTypeDTO == null) {
            return null;
        }
        return service.update(id.toString(), deviceTypeDTO, IdentifierType.ID);
    }

    @PostMapping("/by-name/{name}")
    public DeviceTypeDTO updateByName(@PathVariable("name") String name, @RequestBody DeviceTypeDTO deviceTypeDTO) {
        if (deviceTypeDTO == null || deviceTypeDTO.getName() == null) {
            return null;
        }

        return service.update(name, deviceTypeDTO, IdentifierType.NAME);
    }
}
