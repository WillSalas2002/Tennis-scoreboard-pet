package com.will.service;

import com.will.dto.MatchFilter;
import com.will.model.Match;
import com.will.repository.MatchesRepository;

import java.util.ArrayList;
import java.util.List;

public class MatchesRepresentationService {
    private final MatchesRepository matchesRepository = new MatchesRepository();
    private final int LIMIT = 10;

    public List<Match> findAll(String name, String pageStr) {
        int offset = 0;

        if (pageStr != null) {
            if (pageStr.matches("[1-9]\\d*")) {
                int page = Integer.parseInt(pageStr) - 1;
                if (page > 0) {
                    offset = page * LIMIT;
                }
            } else {
                return null;
            }
        }
        return matchesRepository.findAll(new MatchFilter(name, LIMIT, offset));
    }

    public List<Integer> countPages(String name) {
        List<Integer> pages = new ArrayList<>();
        if (name == null || name.isBlank()) {
            int i = matchesRepository.countMatches() / LIMIT;
            for (int j = 1; j <= i; j++) {
                pages.add(j);
            }
        } else {
            double dataCount = matchesRepository.countMatches(name);
            double pageCount = Math.ceil(dataCount / (double) LIMIT);
            for (int j = 1; j <= pageCount; j++) {
                pages.add(j);
            }
        }
        return pages;
    }
}
