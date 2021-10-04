package ru.borichev.alex.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.borichev.alex.dtos.ProductDto;
import ru.borichev.alex.exceptions.ResourceNotFoundException;
import ru.borichev.alex.model.Category;
import ru.borichev.alex.model.Product;
import ru.borichev.alex.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;


    public List<Product> findAll() {
        return productRepository.findAll();
    }


    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public ProductDto convertToProductDto(Product product){
        ProductDto dto = new ProductDto();
        dto.setPrice(product.getPrice());
        dto.setTitle(product.getTitle());
        dto.setCategoryTitle(product.getCategory().getTitle());
        dto.setId(product.getId());
        return dto;
    }


    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Transactional
    public void updateProductFromDto(ProductDto productDto) {
        Product product = findById(productDto.getId()).orElseThrow(() -> new ResourceNotFoundException("Product id = " + productDto.getId() + " not found"));
        product.setPrice(productDto.getPrice());
        product.setTitle(productDto.getTitle());
        if (!product.getCategory().getTitle().equals(productDto.getCategoryTitle())) {
            Category category = categoryService.findByTitle(productDto.getCategoryTitle()).orElseThrow(() -> new ResourceNotFoundException("Category title = " + productDto.getCategoryTitle() + " not found"));
            product.setCategory(category);
        }
    }

    public Optional<Product> findByTitle(String title) {
        return productRepository.findByTitle(title);
    }
}
