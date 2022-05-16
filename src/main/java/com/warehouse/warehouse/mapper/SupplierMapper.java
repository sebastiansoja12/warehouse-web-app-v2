package com.warehouse.warehouse.mapper;


import com.warehouse.warehouse.dto.SupplierDto;
import com.warehouse.warehouse.model.Supplier;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface SupplierMapper {
    @Mapping(target = "supplierId", source = "id")
    @Mapping(target = "supplierFirstName", source = "firstName")
    @Mapping(target = "supplierLastName", source = "lastName")
    @Mapping(target = "supplierTelephoneNumber", source = "telephone")
    @Mapping(target = "supplierDepotCode", source = "depot.depotCode")
    SupplierDto map(Supplier supplier);

    List<Supplier> mapToList(List<SupplierDto> supplierDtoList);
}
