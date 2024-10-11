package com.GroceryStore.CapStone.Project.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.GroceryStore.CapStone.Project.Models.Seller;
import com.GroceryStore.CapStone.Project.repositories.SellerRepository;

@RestController
@RequestMapping("/sellers")
public class SellerController {

    @Autowired
    private SellerRepository sellerRepository;

    // Get all sellers
    @GetMapping
    public List<Seller> getAllSellers() {
        return sellerRepository.findAll();
    }

    // Get seller by ID
    @GetMapping("/{sellerId}")
    public Seller getSellerById(@PathVariable Long sellerId) {
        return sellerRepository.findById(sellerId); // Adjusted to return the seller directly
    }

    // Add new seller
    @PostMapping
    public Seller addSeller(@RequestBody Seller seller) {
        return sellerRepository.save(seller);
    }

    // Update existing seller
    @PutMapping("/{sellerId}")
    public Seller updateSeller(@PathVariable Long sellerId, @RequestBody Seller sellerDetails) {
        Seller seller = sellerRepository.findById(sellerId); // Adjusted to return the seller directly
        if (seller != null) {
            seller.setName(sellerDetails.getName());
            seller.setContactInfo(sellerDetails.getContactInfo());
            return sellerRepository.save(seller);
        }
        return null; // Return null if the seller is not found
    }

    // Delete seller
    @DeleteMapping("/{sellerId}")
    public void deleteSeller(@PathVariable Long sellerId) {
        sellerRepository.deleteById(sellerId); // Ensure this method is implemented in the repository
    }
}
