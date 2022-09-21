package com.example.Device.Management.design.customer;

import com.example.Device.Management.ConvertUtils;
import com.example.Device.Management.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository repository;
    @Autowired
    private ConvertUtils convertUtils;
    @Autowired
    private CustomerMapper mapper;

    public List<CustomerDTO> findAll() {
        return repository.findAll().stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    public CustomerDTO findById(String id) {
        return mapper.toDTO(repository.findById(id).get());
    }

    public CustomerDTO findByName(String name) {
        return mapper.toDTO(repository.findByName(name));
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }

    @Transactional
    public void deleteByName(String name) {
        repository.deleteByName(name);
    }

    public Customer save(CustomerDTO customerDTO) {
        return repository.save(new Customer(customerDTO));
    }

    @Transactional
    public Customer update(String id, CustomerDTO customerDTO) {
        Customer customer = repository.findById(id).orElseThrow(NotFoundException::new);
        convertUtils.makeCopy(customer, customerDTO);

        return repository.save(customer);
    }
}
