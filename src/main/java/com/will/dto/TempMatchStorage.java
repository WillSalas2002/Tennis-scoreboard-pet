package com.will.dto;

import com.will.service.score_models.MatchScoreModel;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TempMatchStorage extends HashMap<UUID, MatchScoreModel> {

    private static final TempMatchStorage STORAGE = new TempMatchStorage();

    public static void addMatch(UUID uuid, MatchScoreModel matchScoreModel) {
        STORAGE.put(uuid, matchScoreModel);
    }

    public static void removeMatch(UUID uuid) {
        STORAGE.remove(uuid);
    }

    public static MatchScoreModel getMatchScoreModel(UUID uuid) {
        return STORAGE.get(uuid);
    }
}
