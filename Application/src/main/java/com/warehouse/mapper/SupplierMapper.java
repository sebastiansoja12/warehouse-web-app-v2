package com.warehouse.mapper;


import com.warehouse.dto.SupplierDto;
import com.warehouse.entity.Supplier;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface SupplierMapper {

    SupplierDto map(Supplier supplier);

    @Mapping(target = "depot.depotCode", source = "depotCode")
    Supplier mapToDto(SupplierDto supplier);

    List<Supplier> mapToList(List<SupplierDto> supplierDtoList);

}
