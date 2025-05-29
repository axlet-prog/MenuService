package com.example.RestaurantService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MenuRepository extends JpaRepository<MenuItem, Long>{

    Optional<MenuItem> findByTitle(String title);
}
