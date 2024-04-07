package com.will.service;

import com.will.model.Match;
import com.will.repository.FinishedMatchRepository;

public class FinishedMatchesPersistenceService {

    private final FinishedMatchRepository finishedMatchRepository = new FinishedMatchRepository();

    public Match save(Match match) {
        return finishedMatchRepository.save(match);
    }
}
