package com.warehouse.warehouse.mapper;


import com.warehouse.warehouse.dto.CustomerDto;
import com.warehouse.warehouse.model.Customer;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


@AllArgsConstructor
public class CustomerMapperTest {

    private final CustomerMapper mapper;

    @Test
    public void givenCustomerDtoToCustomer() {
        CustomerDto dto = new CustomerDto();
        dto.setId(1L);
        dto.setFirstName("John");

        Customer entity = mapper.map(dto);

        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getFirstName(), entity.getFirstName());
    }
}
