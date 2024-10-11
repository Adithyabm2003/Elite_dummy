package com.GroceryStore.CapStone.Project.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.GroceryStore.CapStone.Project.Models.Product;

import java.util.List;

@Repository
public class ProductRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public List<Product> findAll() {
        TypedQuery<Product> query = entityManager.createQuery("from Product", Product.class);
        return query.getResultList();
    }

    @Transactional
    public List<Product> findByCategoryId(Long categoryId) {
        TypedQuery<Product> query = entityManager.createQuery("from Product where category.id = :categoryId", Product.class);
        query.setParameter("categoryId", categoryId);
        return query.getResultList();
    }

    @Transactional
    public List<Product> findBySellerId(Long sellerId) {
        TypedQuery<Product> query = entityManager.createQuery("from Product where seller.id = :sellerId", Product.class);
        query.setParameter("sellerId", sellerId);
        return query.getResultList();
    }

    @Transactional
    public Product save(Product product) {
        if (product.getId() == null) {
            entityManager.persist(product);  // Save new product
        } else {
            entityManager.merge(product);  // Update existing product
        }
        return product;
    }
}
