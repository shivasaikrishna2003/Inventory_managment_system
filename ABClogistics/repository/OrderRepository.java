package com.alpha.ABClogistics.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alpha.ABClogistics.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{

}
