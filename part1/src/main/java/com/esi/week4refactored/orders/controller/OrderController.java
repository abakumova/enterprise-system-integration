package com.esi.week4refactored.orders.controller;

import com.esi.week4refactored.orders.dto.OrderDto;
import com.esi.week4refactored.orders.service.OrderService;
import com.esi.week4refactored.products.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/products/{productId}/orders")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderDto> getAllOrders(@PathVariable String productId) {
        return orderService.getAllOrders(productId);
    }

    @GetMapping("/products/{productId}/orders/{orderId}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<OrderDto> getOrder(@PathVariable String orderId) {
        return orderService.getOrder(orderId);
    }

    @PostMapping("/products/{productId}/orders")
    @ResponseStatus(HttpStatus.OK)
    public void addOrder(@RequestBody OrderDto orderDto, @PathVariable String productId) {
        orderDto.setProduct(new Product(productId));
        orderService.addOrder(orderDto);
    }

    @PutMapping("/products/{productId}/orders/{orderId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateOrder(@RequestBody OrderDto orderDto, @PathVariable String productId, @PathVariable String orderId) {
        orderDto.setProduct(new Product(productId));
        orderService.updateOrder(orderDto);
    }

    @DeleteMapping("/products/orders/{orderId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteOrder(@PathVariable String orderId) {
        orderService.deleteOrder(orderId);
    }
}
