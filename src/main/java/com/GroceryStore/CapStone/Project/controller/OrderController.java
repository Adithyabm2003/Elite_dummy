package com.GroceryStore.CapStone.Project.controller;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.GroceryStore.CapStone.Project.Models.Order; // Ensure this package name is correct
import com.GroceryStore.CapStone.Project.repositories.OrderRepository;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    // Get all orders
    @GetMapping(produces="application/json")
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // Get order by ID
    @GetMapping(value="/{orderId}",produces="application/json")
    public Order getOrderById(@PathVariable Long orderId) {
        return orderRepository.findById(orderId);
    }

    // Add new order
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Order addOrder(@RequestBody Order order) {
        if (order.getOrderDate() == null) {
            order.setOrderDate(new Date()); // Automatically set the current date
        }
        return orderRepository.save(order);
    }


    // Update existing order
    @PutMapping("/{orderId}")
    public Order updateOrder(@PathVariable Long orderId, @RequestBody Order orderDetails) {
        Order order = orderRepository.findById(orderId);
        if (order != null) {
            order.setOrderStatus(orderDetails.getOrderStatus());
            order.setTotalAmount(orderDetails.getTotalAmount());
            return orderRepository.save(order);
        }
        return null; // Optionally, you might want to return a 404 response if the order is not found
    }

    // Delete order
    @DeleteMapping("/{orderId}")
    public void deleteOrder(@PathVariable Long orderId) {
        orderRepository.deleteById(orderId);
    }
}
