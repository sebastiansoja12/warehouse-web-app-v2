package com.warehouse.warehouse.service;

import com.warehouse.warehouse.data.ProductDataAccessService;
import com.warehouse.warehouse.model.Product;
import com.warehouse.warehouse.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
@Transactional

public class ProductService {

private final ProductRepository productRepository;
private final ProductDataAccessService productDataAccessService;

    @Transactional
    public Product save(Product product){
        product.setCreatedAt(Instant.now());
        return productRepository.save(product);
    }

    @Transactional(readOnly = true)
    public List<Product> findAll(){
        return productRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Product findByKodPaczki(String productCode) {
        return productRepository.findByProductCode(productCode).orElse(null);
    }


    @Transactional
    public void update(Product product, String kod) {

    }
}
