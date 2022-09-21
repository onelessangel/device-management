package com.example.Device.Management;

import com.example.Device.Management.design.customer.Customer;
import com.example.Device.Management.design.customer.CustomerDTO;
import com.example.Device.Management.design.device.Device;
import com.example.Device.Management.design.device.DeviceDTO;
import com.example.Device.Management.design.devicetype.DeviceType;
import com.example.Device.Management.design.devicetype.DeviceTypeDTO;
import com.example.Device.Management.design.firmware.Firmware;
import com.example.Device.Management.design.firmware.FirmwareDTO;
import com.example.Device.Management.design.hardware.Hardware;
import com.example.Device.Management.design.hardware.HardwareDTO;
import org.springframework.stereotype.Component;

@Component
public class ConvertUtils {
    private void copyProperties(Customer customer, CustomerDTO customerDTO) {
        if (customerDTO.getName() != null) {
            customer.setName(customerDTO.getName());
        }

        if (customerDTO.getFirstConnection() != null) {
            customer.setFirstConnection(customerDTO.getFirstConnection());
        }
    }

    private void copyProperties(Device device, DeviceDTO deviceDTO) {
        if (deviceDTO.getMacAddr() != null) {
            device.setMacAddr(deviceDTO.getMacAddr());
        }

        if (deviceDTO.getSerial() != null) {
            device.setSerial(deviceDTO.getSerial());
        }

        if (deviceDTO.getHardwareId() != null) {
            device.setHardwareId(deviceDTO.getHardwareId());
        }

        if (deviceDTO.getFirmwareId() != null) {
            device.setFirmwareId(deviceDTO.getFirmwareId());
        }

        if (deviceDTO.getDevTypeId() != null) {
            device.setDevTypeId(deviceDTO.getDevTypeId());
        }

        if (deviceDTO.getCustomerId() != null) {
            device.setCustomerId(deviceDTO.getCustomerId());
        }

        if (deviceDTO.getFirstConnection() != null) {
            device.setFirstConnection(deviceDTO.getFirstConnection());
        }

        if (deviceDTO.getLastConnection() != null) {
            device.setLastConnection(deviceDTO.getLastConnection());
        }
    }

    private void copyProperties(DeviceType deviceType, DeviceTypeDTO deviceTypeDTO) {
        if (deviceTypeDTO.getName() != null) {
            deviceType.setName(deviceTypeDTO.getName());
        }
    }

    private void copyProperties(Firmware firmware, FirmwareDTO firmwareDTO) {
        if (firmwareDTO.getName() != null) {
            firmware.setName(firmwareDTO.getName());
        }

        if (firmwareDTO.getState() != null) {
            firmware.setState(firmwareDTO.getState());
        }
    }

    private void copyProperties(Hardware hardware, HardwareDTO hardwareDTO) {
        if (hardwareDTO.getName() != null) {
            hardware.setName(hardwareDTO.getName());
        }
    }

    public void makeCopy(Customer customer, CustomerDTO customerDTO) {
        copyProperties(customer, customerDTO);
    }

    public void makeCopy(Device device, DeviceDTO deviceDTO) {
        copyProperties(device, deviceDTO);
    }

    public void makeCopy(DeviceType deviceType, DeviceTypeDTO deviceTypeDTO) {
        copyProperties(deviceType, deviceTypeDTO);
    }

    public void makeCopy(Firmware firmware, FirmwareDTO firmwareDTO) {
        copyProperties(firmware, firmwareDTO);
    }

    public void makeCopy(Hardware hardware, HardwareDTO hardwareDTO) {
        copyProperties(hardware, hardwareDTO);
    }
}
