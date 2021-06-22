package com.alanrusnak.api2048.engine.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;


public class Board {

    private Tile[][] tiles;

    public Board() {
        initializeTiles();
    }

    private void initializeTiles() {
        this.tiles = new Tile[4][4];
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                tiles[i][j] = new Tile();
            }
        }
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public Tile getTileAtIndex(int index){
        return tiles[index % 4][index / 4];
    }

    public void setTileAtIndex(int index, int value){
        tiles[index % 4][index / 4].setValue(value);
    }

    public Tile[] getRow(int rowNumber){
        return tiles[rowNumber];
    }

    public Tile[] getColumn(int columnNumber){
        return new Tile[]{tiles[0][columnNumber],tiles[1][columnNumber],tiles[2][columnNumber],tiles[3][columnNumber]};
    }

    @JsonIgnore
    public List<Integer> getFreeIndexList(){
        List<Integer> freeIndexes = new ArrayList<Integer>();
        for(int i = 0; i < 16; i++){
            if(getTileAtIndex(i).isEmpty()) {
                freeIndexes.add(i);
            }
        }
        return freeIndexes;
    }

    @Override
    public String toString() {
        String boardString = "";
        for(int i = 0; i < 4; i++){
            boardString += "|";
            for(int j = 0; j < 4; j++){
                boardString +=(getTiles()[i][j] + "|");
            }
            boardString += "\n";
        }
        return boardString;
    }
}
