package com.will.service;

import com.will.model.Player;
import com.will.repository.NewMatchRepository;

import java.util.Optional;

public class NewMatchService {

    private final NewMatchRepository newMatchRepository = new NewMatchRepository();

    public Player findOrCreatePlayer(String name) {
        Optional<Player> playerOptional = newMatchRepository.findByName(name);
        if (playerOptional.isEmpty()) {
            return newMatchRepository.save(new Player(name));
        }
        return playerOptional.get();
    }
}
