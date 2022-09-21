package com.example.Device.Management.design.devicetype;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceTypeRepository extends JpaRepository<DeviceType, Long> {  // type of entity and type of Id field
    DeviceType findByName(String name);
    void deleteByName(String name);
}
