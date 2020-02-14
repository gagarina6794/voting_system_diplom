package com.voting.system.project.repository;

import com.voting.system.project.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface DishRepository extends JpaRepository<Dish, Integer> {

    Dish findDishByIdAndMenuId(int id, int menuId);

    List<Dish> findAllByMenuIdOrderByNameAsc(int menuId);

    @Transactional
    @Modifying
    int deleteByIdAndMenuId(int id, int menuId);

    @Transactional
    @Modifying
    @Query("UPDATE Dish d set d.name=:name, d.price=:price WHERE d.id=:id AND d.menu.id=:menuId")
    int setValue(int id, int menuId, String name, int price);
}