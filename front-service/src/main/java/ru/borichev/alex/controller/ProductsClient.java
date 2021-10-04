package ru.borichev.alex.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.borichev.alex.dtos.ProductDto;

import java.util.List;

@FeignClient("products-service")
public interface ProductsClient {
    @GetMapping("/api/v1/products")
    List<ProductDto> findAll();

    @GetMapping("/api/v1/products/{id}")
    ProductDto findById(@PathVariable(value = "id") String id);
}
