package com.warehouse.warehouse.mapper;

import com.warehouse.warehouse.dto.CustomerDto;
import com.warehouse.warehouse.model.Customer;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {
    Customer map(CustomerDto customer);
}
