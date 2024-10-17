package com.GroceryStore.CapStone.Project.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.GroceryStore.CapStone.Project.Models.Order;

import java.util.List;

@Repository
public class OrderRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public List<Order> findAll() {
        try {
            TypedQuery<Order> query = entityManager.createQuery("from Order", Order.class);
            return query.getResultList();
        } catch (Exception e) {
            // Log the error (optional)
            // e.g., logger.error("Error fetching orders", e);
            throw new RuntimeException("Failed to fetch orders", e);
        }
    }


    @Transactional
    public List<Order> findByCustomerId(Long customerId) {
        TypedQuery<Order> query = entityManager.createQuery("from Order where customer.id = :customer_id", Order.class);
        query.setParameter("customer_id", customerId);
        return query.getResultList();
    }

    @Transactional
    public Order findById(Long orderId) {
        return entityManager.find(Order.class, orderId); // Use find method to get the order by ID
    }

    @Transactional
    public Order save(Order order) {
        if (order.getId() == null) {
            entityManager.persist(order);  // Save new order
        } else {
            entityManager.merge(order);  // Update existing order
        }
        return order;
    }

    @Transactional
    public void deleteById(Long orderId) {
        Order order = findById(orderId);
        if (order != null) {
            entityManager.remove(order);  // Remove the order if it exists
        }
    }
}
