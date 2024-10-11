package com.GroceryStore.CapStone.Project.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.GroceryStore.CapStone.Project.Models.Seller;

import java.util.List;

@Repository
public class SellerRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public List<Seller> findAll() {
        TypedQuery<Seller> query = entityManager.createQuery("from Seller", Seller.class);
        return query.getResultList();
    }

    @Transactional
    public Seller findById(Long sellerId) {
        return entityManager.find(Seller.class, sellerId); // Fetch seller by ID
    }

    @Transactional
    public Seller save(Seller seller) {
        if (seller.getId() == null) {
            entityManager.persist(seller);  // Save new seller
        } else {
            entityManager.merge(seller);  // Update existing seller
        }
        return seller;
    }

    @Transactional
    public void deleteById(Long sellerId) {
        Seller seller = findById(sellerId);
        if (seller != null) {
            entityManager.remove(seller); // Remove seller if found
        }
    }
}
