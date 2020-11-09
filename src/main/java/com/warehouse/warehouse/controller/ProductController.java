package com.warehouse.warehouse.controller;


import com.warehouse.warehouse.model.Product;
import com.warehouse.warehouse.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public Product addPaczka(@RequestBody Product product){
        return productService.save(product);
    }
    @GetMapping("/all")
    public List<Product> getAll(){
        return productService.findAll();
    }
    @GetMapping("/{productCode}")
    public Product getProductByProductCode(@PathVariable String productCode){
        return productService.findByKodPaczki(productCode);
    }
    @PutMapping("/{productCode}")
    public void updateProductByProductCode(@RequestBody Product product, @PathVariable String productCode){
         productService.update(product, productCode);
    }
}
