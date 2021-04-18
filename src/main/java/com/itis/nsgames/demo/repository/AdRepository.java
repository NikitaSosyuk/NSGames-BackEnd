package com.itis.nsgames.demo.repository;

import com.itis.nsgames.demo.model.Ad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AdRepository extends JpaRepository<Ad, Integer> {
}
