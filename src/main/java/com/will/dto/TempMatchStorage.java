package com.will.dto;

import com.will.model.Match;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TempMatchStorage extends HashMap<UUID, Match> {

    private static final TempMatchStorage STORAGE = new TempMatchStorage();

    public static void addMatch(UUID uuid, Match match) {
        STORAGE.put(uuid, match);
    }

    public static Match getMatch(UUID uuid) {
        return STORAGE.get(uuid);
    }
}
