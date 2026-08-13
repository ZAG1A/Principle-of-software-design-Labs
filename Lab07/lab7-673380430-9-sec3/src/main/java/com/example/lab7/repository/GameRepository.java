package com.example.lab7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.lab7.model.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
}