package com.example.Device.Management.design.firmware;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FirmwareRepository extends JpaRepository<Firmware, Long> { // type of entity and type of Id field
    Firmware findByName(String name);
    void deleteByName(String name);
}
