package com.itis.nsgames.demo.repository;

import com.itis.nsgames.demo.model.Game;
import com.itis.nsgames.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Integer> {
}
