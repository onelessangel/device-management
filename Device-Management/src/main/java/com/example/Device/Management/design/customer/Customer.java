package com.example.Device.Management.design.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    private String id;
    @Column(unique = true)
    private String name;
    @Column
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date firstConnection;

    public Customer(CustomerDTO customerDTO) {
        this.id = UUID.randomUUID().toString();
        this.name = customerDTO.getName();
        this.firstConnection = customerDTO.getFirstConnection();
    }
}
