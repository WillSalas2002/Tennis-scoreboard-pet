package com.will.service;

import com.will.model.MatchScoreModel;

public class MatchScoreCalculationService {
    public void addScore(int scorerId, MatchScoreModel matchScoreModel) {
        matchScoreModel.addScore(scorerId);
    }
}
