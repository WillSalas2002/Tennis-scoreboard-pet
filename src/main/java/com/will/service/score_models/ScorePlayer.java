package com.will.service.score_models;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;

@Getter @Setter
public class ScorePlayer {
    private Integer id;
    private String name;
    private Point point;
    private int setScore;
    private LinkedList<Integer> matchScore = new LinkedList<>();
    private int gameScore;

    public ScorePlayer(Integer id, String name) {
        this.id = id;
        this.name = name;
        this.point = Point.ZERO;
    }

    public void incrementSetScore() {
        setScore++;
    }

    public void incrementGameScore() {
        gameScore++;
    }
}
