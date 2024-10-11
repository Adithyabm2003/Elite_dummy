package com.GroceryStore.CapStone.Project.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.GroceryStore.CapStone.Project.Models.Category;

import java.util.List;

@Repository
public class CategoryRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public List<Category> findAll() {
        TypedQuery<Category> query = entityManager.createQuery("from Category", Category.class);
        return query.getResultList();
    }

    @Transactional
    public Category save(Category category) {
        if (category.getId() == null) {
            entityManager.persist(category);  // Save new category
        } else {
            entityManager.merge(category);  // Update existing category
        }
        return category;
    }
}
