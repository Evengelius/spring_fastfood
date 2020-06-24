package com.main.fastfood.repositories;

import com.main.fastfood.models.Drink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrinkRepository extends JpaRepository<Drink, Integer> {
    Drink findByName(String burger);
}
