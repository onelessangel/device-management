package com.example.Device.Management.design.device;

import com.example.Device.Management.enums.IdentifierType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/devices")
public class DeviceController {
    @Autowired
    private DeviceService service;

    @GetMapping()
    public List<DeviceDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public DeviceDTO findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        service.deleteById(id);
    }

    @DeleteMapping("/by-mac/{macAddr}")
    public void deleteByMacAddr(@PathVariable("macAddr") String macAddr) {
        service.deleteByMacAddr(macAddr);
    }

    @DeleteMapping("/by-serial/{serial}")
    public void deleteBySerial(@PathVariable("serial") String serial) {
        service.deleteBySerial(serial);
    }

    @PostMapping()
    public String save(@RequestBody DeviceDTO deviceDTO) {
        if (deviceDTO.importantFieldsAreNull()) {
            return "Device does not have valid information.";
        }

        if (!DeviceUtils.isMacAddressValid(deviceDTO.getMacAddr())) {
            return "Device does not have valid MAC Address.";
        }

        if (!DeviceUtils.isSerialValid(deviceDTO.getSerial())) {
            return "Device does not have valid Serial.";
        }

        return service.save(deviceDTO);
    }

    @PostMapping("/{id}")
    public DeviceDTO updateById(@PathVariable("id") Long id, @RequestBody DeviceDTO deviceDTO) {
        if (deviceDTO == null || id == null) {
            return null;
        }

        return service.update(id.toString(), deviceDTO, IdentifierType.ID);
    }

    @PostMapping("/by-mac/{macAddr}")
    public DeviceDTO updateByMacAddress(@PathVariable("macAddr") String macAddr, @RequestBody DeviceDTO deviceDTO) {
        if (deviceDTO == null || macAddr == null) {
            return null;
        }

        return service.update(macAddr, deviceDTO, IdentifierType.MAC_ADDRESS);
    }

    @PostMapping("/by-serial/{serial}")
    public DeviceDTO updateBySerial(@PathVariable("serial") String serial, @RequestBody DeviceDTO deviceDTO) {
        if (deviceDTO == null || serial == null) {
            return null;
        }

        return service.update(serial, deviceDTO, IdentifierType.SERIAL);
    }
}
