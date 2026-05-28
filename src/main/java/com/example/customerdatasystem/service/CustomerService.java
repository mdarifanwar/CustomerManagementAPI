package com.example.customerdatasystem.service;

import com.example.customerdatasystem.entity.Customer;
import com.example.customerdatasystem.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    
    @Autowired
    private CustomerRepository customerRepository;
    
    // Save a new customer
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
    
    // Get all customers
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
    
    // Get customer by ID
    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }
    
    // Get customer by email
    public Optional<Customer> getCustomerByEmail(String email) {
        return customerRepository.findByEmail(email);
    }
    
    // Check if customer exists by email
    public boolean existsByEmail(String email) {
        return customerRepository.existsByEmail(email);
    }
    
    // Update customer
    public Customer updateCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
    
    // Delete customer
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
    
    // Get total count of customers
    public long getCustomerCount() {
        return customerRepository.count();
    }
}
