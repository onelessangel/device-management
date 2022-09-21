package com.example.Device.Management.design.device;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Entity
@Table(name = "devices")
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String macAddr;
    @Column(unique = true)
    private String serial;
    private Long hardwareId;
    private Long firmwareId;
    private Long devTypeId;
    private String customerId;
    private Date firstConnection;
    private Date lastConnection;

    public Device(DeviceDTO deviceDTO) {
        this.macAddr = deviceDTO.getMacAddr();
        this.serial = deviceDTO.getSerial();
        this.hardwareId = deviceDTO.getHardwareId();
        this.firmwareId = deviceDTO.getFirmwareId();
        this.devTypeId = deviceDTO.getDevTypeId();
        this.customerId = deviceDTO.getCustomerId();
        this.firstConnection = deviceDTO.getFirstConnection();
        this.lastConnection = deviceDTO.getLastConnection();
    }
}
