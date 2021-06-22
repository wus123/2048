package com.alanrusnak.api2048.engine;

import com.alanrusnak.api2048.engine.model.Board;
import com.alanrusnak.api2048.engine.model.Tile;
import org.springframework.stereotype.Component;

@Component
public class GameOverDetector {

    public boolean isGameOver(Board board){
        if(!board.getFreeIndexList().isEmpty()){
            return false;
        }

        Tile[][] tiles = board.getTiles();
        for(int i = 0; i<4; i++){
            for(int j = 0; j<4; j++){
                if(canMoveOrMergeTile(tiles,i,j)){
                    return false;
                }
            }
        }
        return true;
    }

    public boolean canMoveOrMergeTile(Tile[][] tiles, int i, int j){
        return  canMoveToNeighbourTile(tiles, tiles[i][j], i+1, j) ||
                canMoveToNeighbourTile(tiles, tiles[i][j], i, j+1);
    }

    private boolean canMoveToNeighbourTile(Tile[][] tiles, Tile tile, int neighbourI, int neighbourJ) {
        return  neighbourI >= 0 && neighbourI <= 3 &&
                neighbourJ >= 0 && neighbourJ <= 3 &&
                (tiles[neighbourI][neighbourJ].isEmpty() ||
                 tiles[neighbourI][neighbourJ].getValue() == tile.getValue());
    }


}
