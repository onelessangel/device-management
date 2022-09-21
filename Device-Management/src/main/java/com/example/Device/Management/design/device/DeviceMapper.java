package com.example.Device.Management.design.device;

import org.mapstruct.Mapper;

@Mapper
public interface DeviceMapper {
    DeviceDTO toDTO(Device device);
}
