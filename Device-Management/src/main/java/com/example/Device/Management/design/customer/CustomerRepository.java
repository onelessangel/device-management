package com.example.Device.Management.design.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {   // type of entity and type of Id field
    Customer findByName(String name);
    void deleteByName(String name);
}
