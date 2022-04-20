package com.warehouse.warehouse.controller;

import com.warehouse.warehouse.dto.CustomerDto;
import com.warehouse.warehouse.model.Customer;
import com.warehouse.warehouse.service.CustomerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping()
    public Customer save(@RequestBody CustomerDto customer) {
        return customerService.save(customer);
    }
}
