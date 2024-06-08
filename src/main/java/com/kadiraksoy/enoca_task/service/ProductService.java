package com.kadiraksoy.enoca_task.service;


import com.kadiraksoy.enoca_task.dto.request.CreateProductRequest;
import com.kadiraksoy.enoca_task.dto.request.UpdateProductRequest;
import com.kadiraksoy.enoca_task.dto.response.ProductResponse;
import com.kadiraksoy.enoca_task.entity.Product;
import com.kadiraksoy.enoca_task.exception.ProductNotFoundException;
import com.kadiraksoy.enoca_task.mapper.ProductMapper;
import com.kadiraksoy.enoca_task.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponse createProduct(CreateProductRequest createProductRequest) {
        if (createProductRequest.getStock() == 0) {
            throw new IllegalArgumentException("Stok sıfır olamaz.");
        }

        Product product = ProductMapper.INSTANCE.createToProduct(createProductRequest);
        productRepository.save(product);
        log.info("Urun oluşturuldu: " + product);
        return ProductMapper.INSTANCE.toProductResponse(product);
    }

    public ProductResponse updateProduct(Long id, UpdateProductRequest updateProductRequest) {
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new ProductNotFoundException(id));
        if (updateProductRequest.getStock() == 0) {
            log.info("Uyarı stok sıfır olarak güncelleniyor.");
            System.out.println("Uyarı stok sıfır olarak güncelleniyor.");// ekranda gözüksün diye yazıyorum.
        }

        product.setName(updateProductRequest.getName());
        product.setPrice(updateProductRequest.getPrice());
        product.setStock(updateProductRequest.getStock());
        product.setLastModifiedDate(LocalDateTime.now());
        productRepository.save(product);
        log.info("Urun güncellendi: " + product);
        return ProductMapper.INSTANCE.toProductResponse(product);
    }

    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new ProductNotFoundException(id));
        productRepository.deleteById(id);
        log.info("Urun silindi: " + product);
        System.out.println("Urun silindi: " + product);
    }

    public ProductResponse getProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new ProductNotFoundException(id));
        return ProductMapper.INSTANCE.toProductResponse(product);
    }

    public List<ProductResponse> getProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(ProductMapper.INSTANCE::toProductResponse).toList();
    }
}
