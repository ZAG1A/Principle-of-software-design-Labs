package com.example.lab7.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.lab7.model.Game;
import com.example.lab7.repository.GameRepository;
import com.example.lab7.strategy.DiscountContext;

@Service
public class GameService {

    private final GameRepository gameRepository;
    private final DiscountContext discountContext;

    public GameService(GameRepository gameRepository, DiscountContext discountContext) {
        this.gameRepository = gameRepository;
        this.discountContext = discountContext;
    }

    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    public Optional<Game> getGameById(Long id) {
        return gameRepository.findById(id);
    }

    public Game saveGame(Game game) {
        return gameRepository.save(game);
    }

    public void deleteGame(Long id) {
        gameRepository.deleteById(id);
    }

    // คำนวณราคาสุทธิหลังหักส่วนลดตาม Strategy
    public double calculateFinalPrice(Game game) {
        if (game.getPrice() == null) return 0.0;
        return discountContext.calculateFinalPrice(game.getDiscountName(), game.getPrice());
    }
}