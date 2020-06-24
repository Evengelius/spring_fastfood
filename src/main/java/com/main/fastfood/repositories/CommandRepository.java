package com.main.fastfood.repositories;

import com.main.fastfood.models.Command;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommandRepository extends JpaRepository<Command, Integer> {
    Command findByLogin(String login);
}
