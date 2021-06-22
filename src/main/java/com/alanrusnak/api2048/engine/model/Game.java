package com.alanrusnak.api2048.engine.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;

public class Game {

    private String id;
    private long score;
    private Board board;
    private boolean isGameOver;
    private LocalDateTime startTime;
    private LocalDateTime lastMoveTime;

    public Game(String id, Board board) {
        this.id = id;
        this.score = 0;
        this.board = board;
        this.isGameOver = false;
        this.startTime = LocalDateTime.now();
        this.lastMoveTime = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public long getScore() {
        return score;
    }

    public void addScore(int score){
        this.score = this.score + score;
    }

    public Board getBoard() {
        return board;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }

    @JsonIgnore
    public LocalDateTime getStartTime() {
        return startTime;
    }

    @JsonIgnore
    public LocalDateTime getLastMoveTime() {
        return lastMoveTime;
    }

    public void setLastMoveTimeToNow(){
        this.lastMoveTime = LocalDateTime.now();
    }
}
