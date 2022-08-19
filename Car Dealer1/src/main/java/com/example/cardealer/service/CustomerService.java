package com.example.cardealer.service;

import com.example.cardealer.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    List<Customer> getAll();

    void saveCustomer(Customer customer);

    void deleteCustomer(Long id);

    Optional<Customer> findCustomerById(Long id);

    Customer getOne(Long id);
}
