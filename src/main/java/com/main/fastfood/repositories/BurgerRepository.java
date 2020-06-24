package com.main.fastfood.repositories;

import com.main.fastfood.models.Burger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BurgerRepository extends JpaRepository<Burger, Integer> {
    Burger findByName(String burger);
}
