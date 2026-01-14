package com.kafkademo.demo.service;

import com.kafkademo.demo.dto.OrderEvent;
import com.kafkademo.demo.entity.Order;
import com.kafkademo.demo.repository.OrderRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
@Data
public class OrderService {
    private final OrderRepository orderRepository;
    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    public void  createOrder(OrderEvent orderEvent){
        // create and save order in db.
        Order order = new Order();
        order.setOrderId(orderEvent.getOrderId());
        order.setPrice(orderEvent.getPrice());
        order.setQuantity(orderEvent.getQuantity());
        order.setProductName(orderEvent.getProductName());
        orderRepository.save(order);

        //publish to kafka

        kafkaTemplate.send("order-topic", orderEvent.getOrderId(), orderEvent);
        log.info("Order event sent to kafka topic order-topic : {}", orderEvent);

    }
}
