package com.esi.week4refactored.orders.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.esi.week4refactored.products.model.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "orderstable")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    private String order_id;
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Product product;
    
   /* 
   @ManyToOne(fetch = FetchType.LAZY, optional = false) indicates that the relation between a Product to Order is many to one mapping. 
   This means a product may have a zero or many orders. However, an order can belong to one product only, which exactly what we need.

   @JoinColumn(name = “id”, nullable = false) indicates Order will have id column which will reference id of the product table. 

   @OnDelete(action = OnDeleteAction.CASCADE) indicates that if a parent product row is deleted in the parent table, 
   the corresponding order row will also be deleted in the orders table.

   @JsonIgnore is used to hide the field, when order is serialised into json response body, 
   which is sent to client. We don’t want the product details to be included in the order response body.
   */
}
