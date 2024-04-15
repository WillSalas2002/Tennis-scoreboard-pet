package com.will.service.score_models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter @Setter
public class ScorePlayer {
    private Integer id;
    private String name;
    private Point point;
    private int setScore;
    private ArrayList<Integer> matches = new ArrayList<>(3);
    private int gameScore;
    private int tieSetScore;

    public ScorePlayer(Integer id, String name) {
        this.id = id;
        this.name = name;
        this.point = Point.ZERO;
        populateMatchesList();
    }

    public void incrementPoint() {
        point = point.increment();
    }

    public void incrementSetScore() {
        setScore++;
    }

    public void incrementTieSetScore() {
        tieSetScore++;
    }

    public void incrementGameScore() {
        gameScore++;
    }

    public void resetPoint() {
        this.point = Point.ZERO;
    }

    public void resetSet() {
        this.setScore = 0;
    }

    public void resetTieSet() {
        this.tieSetScore = 0;
    }

    private void populateMatchesList() {
        for (int i = 0; i < 3; i++) {
            matches.add(i, 0);
        }
    }
}
