package com.example.lab8.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.lab8.model.Product;
import com.example.lab8.model.Review;
import com.example.lab8.repository.ProductRepository;
import com.example.lab8.strategy.DiscountContext;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final DiscountContext discountContext;

    public ProductService(ProductRepository productRepository, DiscountContext discountContext) {
        this.productRepository = productRepository;
        this.discountContext = discountContext;
    }

    public List<Product> getAllProducts() {
        List<Product> products = productRepository.findAll();
        for (Product product : products) {
            double discounted = discountContext.calculateFinalPrice(product.getDiscountType(), product.getPrice());
            product.setDiscountedPrice(discounted);
        }
        return products;
    }

    public Product getProductById(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product != null) {
            double discounted = discountContext.calculateFinalPrice(product.getDiscountType(), product.getPrice());
            product.setDiscountedPrice(discounted);
        }
        return product;
    }

    public void saveProduct(Product product) {
        if (product.getReviews() != null) {
            product.getReviews().removeIf(review -> 
                review.getReviewer() == null || review.getReviewer().trim().isEmpty()
            );
            for (Review review : product.getReviews()) {
                review.setProduct(product);
            }
        }
        productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}