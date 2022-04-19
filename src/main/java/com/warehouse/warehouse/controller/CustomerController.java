package com.warehouse.warehouse.controller;

import com.warehouse.warehouse.model.Customer;
import com.warehouse.warehouse.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customers")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping()
    public Customer save(@RequestBody Customer customer) {
        return customerService.save(customer);
    }
}
