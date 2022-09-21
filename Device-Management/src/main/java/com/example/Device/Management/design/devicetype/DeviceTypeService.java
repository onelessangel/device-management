package com.example.Device.Management.design.devicetype;

import com.example.Device.Management.ConvertUtils;
import com.example.Device.Management.enums.IdentifierType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeviceTypeService {
    @Autowired
    private DeviceTypeRepository repository;
    @Autowired
    private ConvertUtils convertUtils;
    @Autowired
    private DeviceTypeMapper mapper;

    public List<DeviceTypeDTO> findAll() {
        return repository.findAll().stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    public DeviceTypeDTO findById(Long id) {
        return mapper.toDTO(repository.findById(id).get());
    }

    public DeviceTypeDTO findByName(String name) {
        return mapper.toDTO(repository.findByName(name));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public void deleteByName(String name) {
        repository.deleteByName(name);
    }

    public String save(DeviceTypeDTO deviceTypeDTO) {
        return repository.save(new DeviceType(deviceTypeDTO)).getId().toString();
    }

    @Transactional
    public DeviceTypeDTO update(String identifier, DeviceTypeDTO deviceTypeDTO, IdentifierType identifierType) {
        DeviceType deviceType = null;
        switch (identifierType) {
            case ID:
                deviceType = repository.findById(Long.parseLong(identifier)).get();
                break;
            case NAME:
                deviceType = repository.findByName(identifier);
                break;
        }

        convertUtils.makeCopy(deviceType, deviceTypeDTO);

        return mapper.toDTO(repository.save(deviceType));
    }
}
