package com.alanrusnak.api2048.engine.model;

import com.fasterxml.jackson.annotation.JsonValue;

public class Tile {


    private int value;
    private boolean isAlreadyMerged;

    public Tile() {
        this(0);
    }

    public Tile(int value) {
        this.value = value;
        this.isAlreadyMerged = false;
    }

    public boolean isEmpty(){
        return value == 0;
    }

    @JsonValue
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isAlreadyMerged() {
        return isAlreadyMerged;
    }

    public void setAlreadyMerged(boolean alreadyMerged) {
        isAlreadyMerged = alreadyMerged;
    }

    @Override
    public String toString() {
        return "" + value;
    }
}
