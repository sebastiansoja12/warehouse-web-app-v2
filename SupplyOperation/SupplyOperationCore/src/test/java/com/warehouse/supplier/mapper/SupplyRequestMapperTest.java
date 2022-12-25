package com.warehouse.supplier.mapper;

import com.warehouse.supplier.domain.model.Supplier;
import com.warehouse.supplier.dto.SupplierAddRequest;
import com.warehouse.supplier.dto.SupplierDto;
import com.warehouse.supplier.infrastructure.adapter.primary.mapper.SupplierRequestMapper;
import com.warehouse.supplier.infrastructure.adapter.primary.mapper.SupplierRequestMapperImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SupplyRequestMapperTest {

    private SupplierRequestMapper requestMapper;

    @BeforeEach
    void setup() {
        requestMapper = new SupplierRequestMapperImpl();
    }

    @Test
    void shouldMapFromDtoToSupplier() {
        // given
        final SupplierDto dto = new SupplierDto();
        dto.setSupplierCode("Test");
        // when
        final Supplier supplier = requestMapper.map(dto);
        // then
        assertThat(supplier.getSupplierCode()).isEqualTo(dto.getSupplierCode());
    }

    @Test
    void shouldMapFromRequestToSupplier() {
        // given
        final SupplierAddRequest request = new SupplierAddRequest();
        request.setFirstName("test");
        // when
        final Supplier supplier = requestMapper.map(request);
        // then
        assertThat(supplier.getFirstName()).isEqualTo(request.getFirstName());
    }

    @Test
    void shouldMapFromRequestListToSupplierList() {
        // given
        final SupplierAddRequest request = new SupplierAddRequest();
        request.setFirstName("test");
        final List<SupplierAddRequest> requests = new ArrayList<>();
        requests.add(request);
        // when
        final List<Supplier> suppliers = requestMapper.map(requests);
        // then
        assertThat(suppliers.get(0).getFirstName()).isEqualTo(requests.get(0).getFirstName());
    }
}
