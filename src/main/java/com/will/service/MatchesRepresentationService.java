package com.will.service;

import com.will.dto.MatchFilter;
import com.will.model.Match;
import com.will.repository.MatchesRepository;

import java.util.List;

public class MatchesRepresentationService {
    private final MatchesRepository matchesRepository = new MatchesRepository();

    public List<Match> findAll(String name, String pageStr) {
        int limit = 10;
        int offset = 0;

        if (pageStr != null) {
            if (pageStr.matches("[1-9]\\d*")) {
                int page = Integer.parseInt(pageStr) - 1;
                if (page > 0) {
                    offset = page * limit;
                }
            } else {
                return null;
            }
        }

        return matchesRepository.findAll(new MatchFilter(name, limit, offset));
    }
}
