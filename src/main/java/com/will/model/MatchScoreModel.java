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

    public void addScore(int scorerId) {
        if (scorerId == player1.getId()) {
            handleAddingScore(player1, player2);
        } else
            handleAddingScore(player2, player1);
    }

    private void handleAddingScore(ScorePlayer player2, ScorePlayer player1) {
        if (player2.getPoint() == Point.FORTY) {
            if (player1.getPoint() == Point.ADVANTAGE) {
                player1.setPoint(Point.FORTY);
            } else if (player1.getPoint() == Point.FORTY) {
                player2.setPoint(Point.ADVANTAGE);
            } else {
                player1.setPoint(Point.ZERO);
                player2.setPoint(Point.ZERO);
                player2.increaseSetScore();
            }
            if (player2.getSetScore() == 6) {
                player2.setSetScore(0);
                player2.increaseGameScore();
            }
        } else {
            try {
                player2.addPoint();
            } catch (IllegalArgumentException e) {
                player1.setPoint(Point.ZERO);
                player2.setPoint(Point.ZERO);
                player2.increaseSetScore();
            }
        }
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
