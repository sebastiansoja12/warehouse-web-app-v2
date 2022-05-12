package com.warehouse.warehouse.mapper;


import com.warehouse.warehouse.model.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CustomerMapperTest {

    @Autowired
    private CustomerMapper mapper;

    @Test
    public void givenCustomerDtoToCustomer() {
        // given
        CustomerDto dto = new CustomerDto();
        dto.setId(1L);
        dto.setFirstName("John");

        // when

        Customer entity = mapper.map(dto);

        // then
        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getFirstName(), entity.getFirstName());
    }
}
