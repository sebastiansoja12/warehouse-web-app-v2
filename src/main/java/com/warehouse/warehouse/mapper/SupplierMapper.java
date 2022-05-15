package com.warehouse.warehouse.mapper;


import com.warehouse.warehouse.dto.SupplierDto;
import com.warehouse.warehouse.model.Supplier;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import javax.persistence.ManyToOne;
import java.util.List;

@Mapper
public interface SupplierMapper {

    SupplierDto map(Supplier supplier);

    @Mapping(target = "depot.depotCode", source = "depotCode")
    Supplier mapToDto(SupplierDto supplier);

    List<Supplier> mapToList(List<SupplierDto> supplierDtoList);
}
