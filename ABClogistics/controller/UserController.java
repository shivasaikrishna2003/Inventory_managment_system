package com.alpha.ABClogistics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alpha.ABClogistics.DTO.CargoDto;
import com.alpha.ABClogistics.DTO.OrderDto;
import com.alpha.ABClogistics.DTO.ResponseStructure;
import com.alpha.ABClogistics.entity.Cargo;
import com.alpha.ABClogistics.entity.Order;
import com.alpha.ABClogistics.service.CargoService;
import com.alpha.ABClogistics.service.OrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
   @Autowired
	OrderService orderService;

  
   @PostMapping("/placeOrder")
   public ResponseEntity<ResponseStructure<Order>> placeOrder(@Valid @RequestBody OrderDto orderDto){
	   return orderService.placeUserOrder(orderDto);
   }
   
   
   @PostMapping("/cancelorder")
   public ResponseEntity<ResponseStructure<Order>> cancelOrder(@RequestParam int orderid){
	   return orderService.cancelOrder(orderid);
   }
	
}
