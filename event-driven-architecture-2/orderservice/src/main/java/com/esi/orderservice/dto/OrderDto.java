package com.esi.orderservice.dto;

import java.math.BigDecimal;

import com.esi.orderservice.model.OrderStatus;
import com.esi.orderservice.model.PaymentStatus;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Integer id;
    private Integer userId;
    private Integer productId;
    private BigDecimal price;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
}