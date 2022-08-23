package com.warehouse.service;

import com.warehouse.dto.SupplierDto;
import com.warehouse.entity.User;
import com.warehouse.exceptions.DepotNotFoundException;
import com.warehouse.mapper.SupplierMapper;
import com.warehouse.entity.Depot;
import com.warehouse.entity.Supplier;
import com.warehouse.repository.DepotRepository;
import com.warehouse.repository.SupplierRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SupplierService {

    private final SupplierRepository supplierRepository;
    private final DepotRepository depotRepository;
    private final SupplierMapper supplierMapper;

    private final AuthService authService;

    public Supplier findBySupplierCode(String supplierCode) {
        return supplierRepository.findBySupplierCode(supplierCode);
    }

    public List<Supplier> findAllSuppliers() {
        return supplierRepository.findAll();
    }

    public List<Supplier> findAllSupplierByCommonDepotCodes() {
        final Optional<User> user = authService.findCurrentLoggedInUser();
        assert user.isPresent();

        return supplierRepository
                .findAll()
                .stream()
                .filter(s -> s.getDepot().getDepotCode()
                        .equals(user.get()
                                .getDepot()
                                .getDepotCode()))
                .collect(Collectors.toList());
    }


    public Supplier save(SupplierDto supplier){
        return supplierRepository.save(Supplier.builder()
                .supplierCode(supplier.getSupplierCode())
                .firstName(supplier.getFirstName())
                .lastName(supplier.getLastName())
                .telephone(supplier.getTelephone())
                .depot(getDepotByCode(supplier))
                .build());
    }

    public List<Supplier> saveMultipleSuppliers(List<SupplierDto> supplier) {
        return supplierRepository.saveAll(supplierMapper.mapToList(supplier));
    }

    public void delete(String supplierCode){
        supplierRepository.deleteBySupplierCode(supplierCode);
    }

    public Depot getDepotByCode(SupplierDto supplier){
        return depotRepository.findByDepotCode(supplier.getDepotCode()).orElseThrow(() ->
                new DepotNotFoundException("Given depot doesnt exist!"));
    }
}
