package com.alanrusnak.api2048.engine.model;

public class MoveResult {

    private int score;
    private boolean wasTileMoved;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void addScore(int score) {
        this.score = this.score + score;
    }

    public boolean wasTileMoved() {
        return wasTileMoved;
    }

    public void setWasTileMoved(boolean wasTileMoved) {
        this.wasTileMoved = wasTileMoved;
    }
}
