package com.warehouse.warehouse.service;


import com.warehouse.warehouse.model.Customer;
import com.warehouse.warehouse.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    // COUR-0011 will be implemented with CustomerDto later
    public Customer save(Customer customer){
        Customer createCustomer  =  Customer.builder()
                .city(customer.getCity())
                .emailAddress(customer.getEmailAddress())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .postalCode(customer.getPostalCode())
                .street(customer.getStreet())
                .telephoneNumber(customer.getTelephoneNumber())
                .build();

        return customerRepository.save(createCustomer);
    }


}
