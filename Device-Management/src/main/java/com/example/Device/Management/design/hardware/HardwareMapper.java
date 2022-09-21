package com.example.Device.Management.design.hardware;

import org.mapstruct.Mapper;

@Mapper
public interface HardwareMapper {
    HardwareDTO toDTO(Hardware hardware);
}
