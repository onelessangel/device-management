package com.example.Device.Management.design.customer;

import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {
    CustomerDTO toDTO(Customer customer);
}
