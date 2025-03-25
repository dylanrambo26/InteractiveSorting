package com.example.sortinggame.repository;
import com.example.sortinggame.model.GameState;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GameStateRepository extends JpaRepository<GameState, Integer> {
}
