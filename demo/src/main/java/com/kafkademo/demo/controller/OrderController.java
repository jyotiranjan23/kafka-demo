package com.kafkademo.demo.controller;

import com.kafkademo.demo.dto.OrderEvent;
import com.kafkademo.demo.service.OrderService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Data
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    @PostMapping("/save")
    public ResponseEntity<String> createOrder(@RequestBody OrderEvent orderEvent){
        orderService.createOrder(orderEvent);
        return ResponseEntity.status(HttpStatus.CREATED).body("Order Placed Successfully");
    }

}
