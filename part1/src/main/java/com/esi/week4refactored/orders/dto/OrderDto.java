package com.esi.week4refactored.orders.dto;

import com.esi.week4refactored.products.model.Product;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    @Id
    private String order_id;
    private Integer quantity;
    private Product product;
}