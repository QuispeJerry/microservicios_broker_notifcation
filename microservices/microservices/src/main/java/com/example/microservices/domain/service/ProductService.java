package com.example.microservices.domain.service;

import com.example.microservices.domain.model.Product;
import com.example.microservices.domain.repository.ProductRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.amqp.core.AmqpTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final AmqpTemplate amqpTemplate;

    @Autowired
    public ProductService(ProductRepository productRepository, AmqpTemplate amqpTemplate) {
        this.productRepository = productRepository;
        this.amqpTemplate = amqpTemplate;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public Product save(Product product) {
        // Guarda el producto en la base de datos
        Product savedProduct = productRepository.save(product);
        // Envía un mensaje a la cola de RabbitMQ
        amqpTemplate.convertAndSend(RabbitMQConfig.QUEUE_NAME, savedProduct);
        return savedProduct;
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}

