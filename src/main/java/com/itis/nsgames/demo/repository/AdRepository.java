package com.itis.nsgames.demo.repository;

import com.itis.nsgames.demo.model.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface AdRepository extends JpaRepository<Ad, Integer> {
    Optional<Ad> findByIdAndAdState(Integer id, Ad.State state);
}
