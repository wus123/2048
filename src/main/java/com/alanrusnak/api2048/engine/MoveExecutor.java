package com.alanrusnak.api2048.engine;

import com.alanrusnak.api2048.engine.model.Board;
import com.alanrusnak.api2048.engine.model.MoveResult;
import com.alanrusnak.api2048.engine.model.Tile;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Component;

@Component
public class MoveExecutor {

    public MoveResult move(Board board, int direction){
        switch(direction){
            case 0: return boardMoveUp(board);
            case 1: return boardMoveRight(board);
            case 2: return boardMoveDown(board);
            case 3: return boardMoveLeft(board);
            default: throw new IllegalArgumentException("Incorrect move direction " + direction);
        }
    }

    private MoveResult boardMoveUp(Board board){
        MoveResult moveResult = new MoveResult();
        for(int i = 0; i < 4; i++){
            MoveResult columnResult = columnMoveUp(board.getColumn(i));
            moveResult.addScore(columnResult.getScore());
            moveResult.setWasTileMoved(columnResult.wasTileMoved() || moveResult.wasTileMoved());
        }
        return moveResult;
    }

    private MoveResult columnMoveUp(Tile[] column){
        ArrayUtils.reverse(column);
        MoveResult result = slideRight(column);
        ArrayUtils.reverse(column);
        return result;
    }

    private MoveResult boardMoveRight(Board board){
        MoveResult moveResult = new MoveResult();
        for(int i = 0; i < 4; i++){
            MoveResult rowResult = rowMoveRight(board.getRow(i));
            moveResult.addScore(rowResult.getScore());
            moveResult.setWasTileMoved(rowResult.wasTileMoved() || moveResult.wasTileMoved());
        }
        return moveResult;
    }

    private MoveResult rowMoveRight(Tile[] row){
        return slideRight(row);
    }

    private MoveResult boardMoveDown(Board board){
        MoveResult moveResult = new MoveResult();
        for(int i = 0; i < 4; i++){
            MoveResult columnResult = columnMoveDown(board.getColumn(i));
            moveResult.addScore(columnResult.getScore());
            moveResult.setWasTileMoved(columnResult.wasTileMoved() || moveResult.wasTileMoved());
        }
        return moveResult;
    }

    private MoveResult columnMoveDown(Tile[] column){
        return slideRight(column);
    }

    private MoveResult boardMoveLeft(Board board){
        MoveResult moveResult = new MoveResult();
        for(int i = 0; i < 4; i++){
            MoveResult rowResult = rowMoveLeft(board.getRow(i));
            moveResult.addScore(rowResult.getScore());
            moveResult.setWasTileMoved(rowResult.wasTileMoved() || moveResult.wasTileMoved());
        }
        return moveResult;
    }

    private MoveResult rowMoveLeft(Tile[] row){
        ArrayUtils.reverse(row);
        MoveResult moveResult = slideRight(row);
        ArrayUtils.reverse(row);
        return moveResult;
    }

    public MoveResult slideRight(Tile[] row){
        MoveResult moveResult = new MoveResult();
        for(int i = 2; i >= 0; i--){
            if(!row[i].isEmpty()){
                MoveResult slideResult = slideTileRight(row, i);
                moveResult.addScore(slideResult.getScore());
                moveResult.setWasTileMoved(slideResult.wasTileMoved() || moveResult.wasTileMoved());
            }
        }
        clearAlreadyMergedFlags(row);
        return moveResult;
    }

    private MoveResult slideTileRight(Tile[] row, int index) {
        MoveResult moveResult = new MoveResult();
        for(int i = index; i <= 2; i++){
            if(row[i+1].isEmpty()){
                row[i+1].setValue(row[i].getValue());
                row[i].setValue(0);
                moveResult.setWasTileMoved(true);
            } else if(row[i].getValue() == row[i+1].getValue() && !row[i+1].isAlreadyMerged()){
                row[i+1].setValue(row[i+1].getValue() * 2);
                row[i+1].setAlreadyMerged(true);
                row[i].setValue(0);
                moveResult.addScore(row[i+1].getValue());
                moveResult.setWasTileMoved(true);
                break;
            } else {
                break;
            }
        }
        return moveResult;
    }

    private void clearAlreadyMergedFlags(Tile[] row) {
        for(int i = 0; i < 4; i++){
            row[i].setAlreadyMerged(false);
        }
    }

}
