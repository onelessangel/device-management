package com.example.Device.Management.design.device;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class DeviceDTO {
    private String macAddr;
    private String serial;
    private Long hardwareId;
    private Long firmwareId;
    private Long devTypeId;
    private String customerId;
    private Date firstConnection;
    private Date lastConnection;

    boolean importantFieldsAreNull() {
        if (macAddr == null
                || serial == null
                || hardwareId == null
                || firmwareId == null
                || devTypeId == null
                || customerId == null) {
            return true;
        }

        return false;
    }
}
