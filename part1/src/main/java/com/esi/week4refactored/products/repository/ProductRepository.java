package com.esi.week4refactored.products.repository;

import org.springframework.data.repository.CrudRepository;

import com.esi.week4refactored.products.model.Product;

public interface ProductRepository extends CrudRepository<Product, String> {

}
