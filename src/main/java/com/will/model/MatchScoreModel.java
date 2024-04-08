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

    public MatchScoreModel(Player player1, Player player2) {
        this.player1 = new ScorePlayer(player1.getId(), player1.getName());
        this.player2 = new ScorePlayer(player2.getId(), player2.getName());
    }

    public void addScore(int scoringPlayerId) {
        if (scoringPlayerId == player1.getId())
            handleAddingScore(player1, player2);
        else
            handleAddingScore(player2, player1);
    }

    private void handleAddingScore(ScorePlayer scoringPlayer, ScorePlayer opponentPlayer) {
        if (scoringPlayer.getPoint() == Point.FORTY || scoringPlayer.getPoint() == Point.ADVANTAGE) {
            if (opponentPlayer.getPoint() == Point.ADVANTAGE) {
                opponentPlayer.setPoint(Point.FORTY);
            } else if (scoringPlayer.getPoint() == Point.ADVANTAGE) {
                resetPointsAndIncrementSetScore(scoringPlayer, opponentPlayer);
            } else if (opponentPlayer.getPoint() == Point.FORTY) {
                scoringPlayer.setPoint(Point.ADVANTAGE);
            } else {
                resetPointsAndIncrementSetScore(scoringPlayer, opponentPlayer);
            }

            if (scoringPlayer.getSetScore() == 6) {
                opponentPlayer.getMatchScore().add(opponentPlayer.getSetScore());
                scoringPlayer.getMatchScore().add(scoringPlayer.getSetScore());
                opponentPlayer.setSetScore(0);
                scoringPlayer.setSetScore(0);
                scoringPlayer.incrementGameScore();
            }
        } else {
            try {
                scoringPlayer.setPoint(scoringPlayer.getPoint().increment());
            } catch (IllegalStateException e) {
                resetPointsAndIncrementSetScore(scoringPlayer, opponentPlayer);
            }
        }
    }

    private void resetPointsAndIncrementSetScore(ScorePlayer scoringPlayer, ScorePlayer opponentPlayer) {
        scoringPlayer.setPoint(Point.ZERO);
        opponentPlayer.setPoint(Point.ZERO);
        scoringPlayer.incrementSetScore();
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
