package com.itis.nsgames.demo.repository;

import com.itis.nsgames.demo.model.Game;
import com.itis.nsgames.demo.model.PhotoName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<PhotoName, Integer> {
}
