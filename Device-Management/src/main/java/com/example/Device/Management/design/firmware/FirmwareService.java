package com.example.Device.Management.design.firmware;

import com.example.Device.Management.ConvertUtils;
import com.example.Device.Management.enums.IdentifierType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FirmwareService {
    @Autowired
    FirmwareRepository repository;
    @Autowired
    FirmwareMapper mapper;
    @Autowired
    ConvertUtils convertUtils;

    public List<FirmwareDTO> findAll() {
        return repository.findAll().stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    public FirmwareDTO findById(Long id) {
        return mapper.toDTO(repository.findById(id).get());
    }

    public FirmwareDTO findByName(String name) {
        return mapper.toDTO(repository.findByName(name));
    }
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public void deleteByName(String name) {
        repository.deleteByName(name);
    }

    public String save(FirmwareDTO firmwareDTO) {
        return repository.save(new Firmware(firmwareDTO)).getId().toString();
    }

    @Transactional
    public FirmwareDTO update(String identifier, FirmwareDTO firmwareDTO, IdentifierType identifierType) {
        Firmware firmware = null;
        switch (identifierType) {
            case ID:
                firmware = repository.findById(Long.parseLong(identifier)).get();
                break;
            case NAME:
                firmware = repository.findByName(identifier);
                break;
        }

        convertUtils.makeCopy(firmware, firmwareDTO);

        return mapper.toDTO(repository.save(firmware));
    }
}
