package com.will.model;

import com.will.service.score_models.Point;
import com.will.service.score_models.ScorePlayer;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatchScoreModel {
    private final ScorePlayer player1;
    private final ScorePlayer player2;
    private ScorePlayer winner;
    private boolean isSixAll;

    public MatchScoreModel(Player player1, Player player2) {
        this.player1 = new ScorePlayer(player1.getId(), player1.getName());
        this.player2 = new ScorePlayer(player2.getId(), player2.getName());
    }

    public void addScore(int scoringPlayerId) {
        ScorePlayer scoredPlayer = scoringPlayerId == player1.getId() ? player1 : player2;
        ScorePlayer opponentPlayer = scoringPlayerId == player1.getId() ? player2 : player1;
        if (isSixAll) {
            handleTieBreak(scoredPlayer, opponentPlayer);
        } else {
            handleScoreAdding(scoredPlayer, opponentPlayer);
        }

    }

    private void handleTieBreak(ScorePlayer scoredPlayer, ScorePlayer opponentPlayer) {
        scoredPlayer.incrementSetScore();
        if (scoredPlayer.getSetScore() >= 7) {
            int subtraction = subtractSetValues(scoredPlayer, opponentPlayer);
            if (subtraction >= 2) {
                scoredPlayer.setSetScore(7);
                opponentPlayer.setSetScore(6);
                saveFinishedSet(scoredPlayer, opponentPlayer);
                resetSetAndIncrementGameScore(scoredPlayer, opponentPlayer);
                isSixAll = false;
            }
        }
    }

    private void handleScoreAdding(ScorePlayer scoredPlayer, ScorePlayer opponentPlayer) {

        if (scoredPlayer.getPoint() == Point.FORTY || scoredPlayer.getPoint() == Point.ADVANTAGE) {

            if (opponentPlayer.getPoint() == Point.ADVANTAGE) {
                opponentPlayer.setPoint(Point.FORTY);
            } else if (scoredPlayer.getPoint() == Point.ADVANTAGE) {
                resetPointsAndIncrementSetScore(scoredPlayer, opponentPlayer);
            } else if (opponentPlayer.getPoint() == Point.FORTY) {
                scoredPlayer.setPoint(Point.ADVANTAGE);
            } else {
                resetPointsAndIncrementSetScore(scoredPlayer, opponentPlayer);
            }

            if (scoredPlayer.getSetScore() >= 6) {
                int subtractionOfSets = subtractSetValues(scoredPlayer, opponentPlayer);
                if (subtractionOfSets >= 2) {
                    saveFinishedSet(scoredPlayer, opponentPlayer);
                    resetSetAndIncrementGameScore(scoredPlayer, opponentPlayer);
                } else if (subtractionOfSets == 0) {
                    isSixAll = true;
                }
            }
        } else {
            try {
                scoredPlayer.incrementPoint();
            } catch (IllegalStateException e) {
                resetPointsAndIncrementSetScore(scoredPlayer, opponentPlayer);
            }
        }
    }

    private int subtractSetValues(ScorePlayer scoredPlayer, ScorePlayer opponentPlayer) {
        return scoredPlayer.getSetScore() - opponentPlayer.getSetScore();
    }

    private void resetSetAndIncrementGameScore(ScorePlayer scoredPlayer, ScorePlayer opponentPlayer) {
        opponentPlayer.resetSet();
        scoredPlayer.resetSet();
        scoredPlayer.incrementGameScore();
    }

    private void saveFinishedSet(ScorePlayer scoredPlayer, ScorePlayer opponentPlayer) {
        opponentPlayer.getMatches().add(opponentPlayer.getSetScore());
        scoredPlayer.getMatches().add(scoredPlayer.getSetScore());
    }

    private void resetPointsAndIncrementSetScore(ScorePlayer scoredPlayer, ScorePlayer opponentPlayer) {
        scoredPlayer.resetPoint();
        opponentPlayer.resetPoint();
        scoredPlayer.incrementSetScore();
    }

    public boolean isGameFinished() {
        if (player1.getGameScore() == 2) {
            this.winner = player1;
        }
        if (player2.getGameScore() == 2) {
            this.winner = player2;
        }
        return winner != null;
    }
}
