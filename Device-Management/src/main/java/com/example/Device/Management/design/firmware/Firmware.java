package com.example.Device.Management.design.firmware;

import com.example.Device.Management.enums.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Entity
@Table(name = "firmwares")
public class Firmware {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private State state;

    public Firmware(FirmwareDTO firmwareDTO) {
        this.name = firmwareDTO.getName();
        this.state = firmwareDTO.getState();
    }
}
