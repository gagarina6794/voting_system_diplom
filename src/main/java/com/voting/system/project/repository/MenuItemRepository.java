package com.voting.system.project.repository;

import com.voting.system.project.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface MenuItemRepository extends JpaRepository<MenuItem, Integer> {

    @Query("SELECT mi FROM MenuItem mi LEFT JOIN FETCH mi.dish d WHERE mi.restaurant.id=:restaurantId ORDER BY mi.date DESC")
    List<MenuItem> findAllByRestaurantId(int restaurantId);

    @Query("SELECT mi FROM MenuItem mi LEFT JOIN FETCH mi.dish d WHERE mi.id=:id and mi.restaurant.id=:restaurantId")
    MenuItem findByIdAndRestaurantId(int id, int restaurantId);
}