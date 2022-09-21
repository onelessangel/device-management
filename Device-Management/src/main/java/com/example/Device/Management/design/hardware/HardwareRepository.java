package com.example.Device.Management.design.hardware;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HardwareRepository extends JpaRepository<Hardware, Long> {
    Hardware findByName(String name);
    void deleteByName(String name);
}
