package com.will.service;

import com.will.model.MatchScoreModel;

public class MatchScoreCalculationService {
    public void addScore(int scoringPlayerId, MatchScoreModel matchScoreModel) {
        matchScoreModel.addScore(scoringPlayerId);
    }
}
