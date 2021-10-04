package ru.borichev.alex.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.borichev.alex.dtos.ProductDto;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductsClient productsClient;

    @GetMapping
    public List<ProductDto> findAll() {
        return productsClient.findAll();
    }

    @GetMapping("/{id}")
    public ProductDto findById(@PathVariable String id) {
        return productsClient.findById(id);
    }

}
