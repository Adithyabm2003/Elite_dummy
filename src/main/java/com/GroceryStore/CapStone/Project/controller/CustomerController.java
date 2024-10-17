package com.GroceryStore.CapStone.Project.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.GroceryStore.CapStone.Project.Models.Customer;
import com.GroceryStore.CapStone.Project.repositories.CustomerRepository;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    // Get all customers
    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // Get customer by ID
    @GetMapping(value="/{customerId}",produces="application/json")
    public Customer getCustomerById(@PathVariable Long customerId) {
        return customerRepository.findById(customerId);
    }

    // Add new customer
    @PostMapping(produces="application/json")
    public Customer addCustomer(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    // Update existing customer
    @PutMapping(value="/{customerId}",produces="application/json")
    public Customer updateCustomer(@PathVariable Long customerId, @RequestBody Customer customerDetails) {
        Customer customer = customerRepository.findById(customerId);
        if (customer != null) {
            customer.setName(customerDetails.getName());
            customer.setEmail(customerDetails.getEmail());
            customer.setAddress(customerDetails.getAddress());
            return customerRepository.save(customer);
        }
        return null;
    }

    // Delete customer
    @DeleteMapping("/{customerId}")
    public void deleteCustomer(@PathVariable Long customerId) {
        customerRepository.deleteById(customerId);
    }
}
