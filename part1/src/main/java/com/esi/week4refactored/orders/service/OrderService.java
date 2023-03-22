package com.esi.week4refactored.orders.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.esi.week4refactored.orders.dto.OrderDto;
import com.esi.week4refactored.orders.model.Order;
import com.esi.week4refactored.orders.repository.OrderRepository;
import com.esi.week4refactored.products.dto.ProductDto;
import com.esi.week4refactored.products.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<OrderDto> getAllOrders(String productId) {
//        List<Order> orders = new ArrayList<>();
//        orderRepository.findByProductId(productId).forEach(orders::add);
//        return orders;
        List<Order> orders = new ArrayList<>();
        orderRepository.findByProductId(productId).forEach(orders::add);
        return orders.stream().map(this::mapToOrderDto).toList();
    }

    private OrderDto mapToOrderDto(Order order) {
        return OrderDto.builder()
                .order_id(order.getOrder_id())
                .quantity(order.getQuantity())
                .product(order.getProduct())
                .build();
    }

    public Optional<OrderDto> getOrder(String id) {
//        return orderRepository.findById(id);
        Optional<Order> order = orderRepository.findById(id);
        return order.map(this::mapToOrderDto);
    }

    public void addOrder(OrderDto orderDto) {
        Order order = Order.builder()
                .order_id(orderDto.getOrder_id())
                .quantity(orderDto.getQuantity())
                .product(orderDto.getProduct())
                .build();
        orderRepository.save(order);
        log.info("Order {} is added to the Database", order.getOrder_id());
    }

    public void updateOrder(OrderDto orderDto) {
        Order order = Order.builder()
                .order_id(orderDto.getOrder_id())
                .quantity(orderDto.getQuantity())
                .product(orderDto.getProduct())
                .build();
        orderRepository.save(order);
        log.info("Order {} is updated", order.getOrder_id());
    }

    public void deleteOrder(String id) {
        orderRepository.deleteById(id);
        log.info("An Order has been deleted");
    }

}