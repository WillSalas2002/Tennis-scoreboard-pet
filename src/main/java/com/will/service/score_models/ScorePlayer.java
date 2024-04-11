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
    private LinkedList<Integer> matches = new LinkedList<>();
    private int gameScore;

    public ScorePlayer(Integer id, String name) {
        this.id = id;
        this.name = name;
        this.point = Point.ZERO;
    }

    public void incrementPoint() {
        point = point.increment();
    }

    public void incrementSetScore() {
        setScore++;
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
}
