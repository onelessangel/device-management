package com.example.Device.Management.design.devicetype;

import org.mapstruct.Mapper;

@Mapper
public interface DeviceTypeMapper {
    DeviceTypeDTO toDTO(DeviceType deviceType);
    DeviceType fromDTO(DeviceTypeDTO deviceTypeDTO);
}
