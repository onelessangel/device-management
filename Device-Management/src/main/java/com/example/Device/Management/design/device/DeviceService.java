package com.example.Device.Management.design.device;

import com.example.Device.Management.ConvertUtils;
import com.example.Device.Management.enums.IdentifierType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeviceService {
    @Autowired
    private DeviceRepository repository;
    @Autowired
    private ConvertUtils convertUtils;
    @Autowired
    private DeviceMapper mapper;

    public List<DeviceDTO> findAll() {
        return repository.findAll().stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    public DeviceDTO findById(Long id) {
        return mapper.toDTO(repository.findById(id).get());
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public void deleteByMacAddr(String macAddr) {
        repository.deleteByMacAddr(macAddr);
    }

    @Transactional
    public void deleteBySerial(String serial) {
        repository.deleteBySerial(serial);
    }

    public String save(DeviceDTO deviceDTO) {
        return repository.save(new Device(deviceDTO)).getId().toString();
    }

    @Transactional
    public DeviceDTO update(String identifier, DeviceDTO deviceDTO, IdentifierType identifierType) {
        Device device = null;
        switch (identifierType) {
            case ID:
                device = repository.findById(Long.parseLong(identifier)).get();
                break;
            case MAC_ADDRESS:
                device = repository.findByMacAddr(identifier);
                break;
            case SERIAL:
                device = repository.findBySerial(identifier);
                break;
        }

        convertUtils.makeCopy(device, deviceDTO);

        return mapper.toDTO(repository.save(device));
    }
}
