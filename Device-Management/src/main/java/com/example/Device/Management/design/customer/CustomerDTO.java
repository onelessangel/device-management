package com.example.Device.Management.design.customer;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class CustomerDTO {
    private String name;
    private Date firstConnection;
}
