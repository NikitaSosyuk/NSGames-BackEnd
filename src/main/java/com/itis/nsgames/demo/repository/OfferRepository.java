package com.itis.nsgames.demo.repository;

import com.itis.nsgames.demo.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferRepository extends JpaRepository<Offer, Integer> {
}
