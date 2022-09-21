package com.example.Device.Management.design.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // mark class as Controller
@RequestMapping("/customers")
public class CustomerController {
    @Autowired  // autowire the CustomerService class
    private CustomerService service;
    @Autowired
    private CustomerMapper mapper;

    @GetMapping()
    public List<CustomerDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public CustomerDTO findById(@PathVariable("id") String id) {
        return service.findById(id);
    }

    @GetMapping("/by-name/{name}")
    public CustomerDTO findByName(@PathVariable("name") String name) {
        return service.findByName(name);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") String id) {
        service.deleteById(id);
    }

    @DeleteMapping("/by-name/{name}")
    public void deleteByName(@PathVariable("name") String name) {
        service.deleteByName(name);
    }

    @PostMapping()
    public String save(@RequestBody CustomerDTO customerDTO) {
        if (customerDTO == null) {
            return "Customer does not have valid information.";
        }

        return service.save(customerDTO).getId();
    }

    @PostMapping("/{id}")
    public CustomerDTO update(@PathVariable("id") String id, @RequestBody CustomerDTO customerDTO) {
        if (customerDTO == null || id == null) {
            return null;
        }

        Customer updatedCustomer = service.update(id, customerDTO);
        return mapper.toDTO(updatedCustomer);
    }
}
