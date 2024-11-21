package com.example.skgsss.service;

import com.example.skgsss.Repository.OrderRepository;
import com.example.skgsss.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    public Order createOrder(Order order){
        return orderRepository.save(order);
    }
    public Order getOrderById(Long id){
        return orderRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Order not found"));
    }
    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }
    public Order updateOrder(Long id,Order order){
        Order existingOrder=getOrderById(id);
        existingOrder.setOrderNumber(order.getOrderNumber());
        existingOrder.setOrderDate(order.getOrderDate());
        return orderRepository.save(existingOrder);
    }
    public void deleteOrder(Long id){
        orderRepository.deleteById(id);
    }


}
