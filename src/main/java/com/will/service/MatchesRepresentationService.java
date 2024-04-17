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
            double dataCount = matchesRepository.countMatches();
            countPages(pages, dataCount);
        } else {
            double dataCount = matchesRepository.countMatches(name);
            countPages(pages, dataCount);
        }
        return pages;
    }

    private void countPages(List<Integer> pages, double dataCount) {
        int pageCount = (int) Math.ceil(dataCount / LIMIT);
        for (int i = 1; i <= pageCount; i++) {
            pages.add(i);
        }
    }
}
