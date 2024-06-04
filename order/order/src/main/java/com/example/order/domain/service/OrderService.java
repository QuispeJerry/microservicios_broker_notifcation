package com.example.order.domain.service;

import com.example.order.domain.model.Order;
import com.example.order.domain.model.Product;
import com.example.order.domain.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.amqp.core.AmqpTemplate;

import org.springframework.web.client.RestTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import java.util.List;

//import java.util.List;
//
//
//@Service
//@Transactional
//public class OrderService {
//
//    private final OrderRepository orderRepository;
//    private final AmqpTemplate amqpTemplate;
//
//    @Autowired
//    public OrderService(OrderRepository orderRepository, AmqpTemplate amqpTemplate) {
//        this.orderRepository = orderRepository;
//        this.amqpTemplate = amqpTemplate;
//    }
//
//    public List<Order> findAll() {
//        return orderRepository.findAll();
//    }
//
//    public void saveOrderRequest(OrderRequest orderRequest) {
//        amqpTemplate.convertAndSend(RabbitMQConfig.ORDER_QUEUE_NAME, orderRequest);
//    }
//
//    public Order save(Order order) {
//        return orderRepository.save(order);
//    }
//
//    public void deleteById(Long id) {
//        orderRepository.deleteById(id);
//    }
//}
//
//
//
//
//
//
//





@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order save(Order order) {
        // Lógica para obtener el producto antes de guardar la orden
        // Aquí podrías usar el broker para manejar eventos de producto creado
        return orderRepository.save(order);
    }

    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void receiveProduct(Product product) {
        System.out.println("Received product: " + product);
        // Aquí puedes manejar el producto recibido, por ejemplo, actualizar inventario
    }
}
