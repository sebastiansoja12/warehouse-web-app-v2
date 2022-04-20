package com.warehouse.warehouse.service;


import com.warehouse.warehouse.dto.CustomerDto;
import com.warehouse.warehouse.model.Customer;
import com.warehouse.warehouse.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Customer save(CustomerDto customerFromRequest){
        Customer createCustomer  =  Customer.builder()
                .city(customerFromRequest.getCity())
                .emailAddress(customerFromRequest.getEmailAddress())
                .firstName(customerFromRequest.getFirstName())
                .lastName(customerFromRequest.getLastName())
                .postalCode(customerFromRequest.getPostalCode())
                .street(customerFromRequest.getStreet())
                .telephoneNumber(customerFromRequest.getTelephoneNumber())
                .build();

        return customerRepository.save(createCustomer);
    }


}
