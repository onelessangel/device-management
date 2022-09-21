package com.example.Device.Management.design.device;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {   // type of entity and type of Id field
    Device findByMacAddr(String macAddr);
    Device findBySerial(String serial);
    void deleteByMacAddr(String macAddr);
    void deleteBySerial(String serial);
}
