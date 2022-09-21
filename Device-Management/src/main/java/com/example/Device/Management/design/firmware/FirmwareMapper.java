package com.example.Device.Management.design.firmware;

import org.mapstruct.Mapper;

@Mapper
public interface FirmwareMapper {
    FirmwareDTO toDTO(Firmware firmware);
}
