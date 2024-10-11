package com.GroceryStore.CapStone.Project.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.GroceryStore.CapStone.Project.Models.Customer;

import java.util.List;

@Repository
public class CustomerRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public List<Customer> findAll() {
        TypedQuery<Customer> query = entityManager.createQuery("from Customer", Customer.class);
        return query.getResultList();
    }
    
    @Transactional
    public void deleteById(Long id) {
        Customer customer = findById(id);
        if (customer != null) {
            entityManager.remove(customer);
        }
    }

    @Transactional
    public Customer findById(Long id) {
        return entityManager.find(Customer.class, id);
    }

    @Transactional
    public Customer save(Customer customer) {
        if (customer.getId() == null) {
            entityManager.persist(customer);  // Save new customer
        } else {
            entityManager.merge(customer);  // Update existing customer
        }
        return customer;
    }
}
