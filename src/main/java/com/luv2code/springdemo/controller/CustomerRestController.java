package com.luv2code.springdemo.controller;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.exception.CustomerNotFoundException;
import com.luv2code.springdemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/readcustomers/all")
    public List<Customer> getAllCustomers () {

        return customerService.getCustomers();
    }

    @GetMapping("/readcustomer/{id}")
    public Customer getCustomer(@PathVariable Integer id) {
        Customer costumer = customerService.getCustomer(id);

        if(costumer == null)
            throw new CustomerNotFoundException("Customer id not found - " + id);

        return customerService.getCustomer(id);
    }

    @PostMapping("/addcustomer")
    public Customer addCustomer(@RequestBody Customer customer) {
        customer.setId(0);
        customerService.saveCustomer(customer);
        return customer;
    }

    @PutMapping("/updatecustomer")
    public Customer updateCustomer(@RequestBody Customer customer) {
        customerService.saveCustomer(customer);
        return customer;
    }

    @DeleteMapping("/deletecustomer/{id}")
    public String deleteCustomer(@PathVariable Integer id) {

        Customer tempCustomer = customerService.getCustomer(id);

        if(tempCustomer == null)
            throw new CustomerNotFoundException("Customer id not found - " + id);

        customerService.deleteCustomer(id);
        return "The customer has been successfully removed from the database";
    }
}
