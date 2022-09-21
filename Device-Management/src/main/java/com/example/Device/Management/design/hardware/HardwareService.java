package com.example.Device.Management.design.hardware;

import com.example.Device.Management.ConvertUtils;
import com.example.Device.Management.enums.IdentifierType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HardwareService {
    @Autowired
    HardwareRepository repository;
    @Autowired
    HardwareMapper mapper;
    @Autowired
    ConvertUtils convertUtils;

    public List<HardwareDTO> findAll() {
        return repository.findAll().stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    public HardwareDTO findById(Long id) {
        return mapper.toDTO(repository.findById(id).get());
    }

    public HardwareDTO findByName(String name) {
        return mapper.toDTO(repository.findByName(name));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public void deleteByName(String name) {
        repository.deleteByName(name);
    }

    public String save(HardwareDTO hardwareDTO) {
        return repository.save(new Hardware(hardwareDTO)).getId().toString();
    }


    public HardwareDTO update(String identifier, HardwareDTO hardwareDTO, IdentifierType identifierType) {
        Hardware hardware = null;
        switch (identifierType) {
            case ID:
                hardware = repository.findById(Long.parseLong(identifier)).get();
                break;
            case NAME:
                hardware = repository.findByName(identifier);
                break;
        }

        convertUtils.makeCopy(hardware, hardwareDTO);

        return mapper.toDTO(repository.save(hardware));
    }
}
