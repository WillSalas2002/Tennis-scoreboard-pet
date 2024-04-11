package com.will.service;

import com.will.service.score_models.MatchScoreModel;

public class MatchScoreCalculationService {
    public void addScore(int scoringPlayerId, MatchScoreModel matchScoreModel) {
        matchScoreModel.addScore(scoringPlayerId);
    }
}
