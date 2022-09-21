package com.example.Device.Management.design.firmware;

import com.example.Device.Management.enums.State;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FirmwareDTO {
    private String name;
    private State state;
}
