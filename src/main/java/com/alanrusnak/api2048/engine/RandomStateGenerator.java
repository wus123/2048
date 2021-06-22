package com.alanrusnak.api2048.engine;

import com.alanrusnak.api2048.engine.model.Board;
import com.alanrusnak.api2048.engine.util.BoardPrinter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

@Component
public class RandomStateGenerator {

    public String generateGameId(){
        return UUID.randomUUID().toString();
    }

    public Board generateNewBoard(){
        Board board = new Board();
        generateNextRandomTile(board);
        generateNextRandomTile(board);
        return board;
    }

    public void generateNextRandomTile(Board board){
        int randomValue = getRandomTileValue();
        int index = getRandomFreeTileIndex(board);
        board.setTileAtIndex(index, randomValue);
    }

    private int getRandomTileValue(){
        return Math.random() < 0.9 ? 2 : 4;
    }

    private int getRandomFreeTileIndex(Board board) {
        List<Integer> freeIndexes = board.getFreeIndexList();
        return freeIndexes.get((int)(Math.random() * freeIndexes.size()));
    }

    public static void main(String[] args){
        RandomStateGenerator rsg = new RandomStateGenerator();
        Board board = rsg.generateNewBoard();
        for(int i = 0; i < 20; i++) {
            BoardPrinter.printBoard(board);
            Scanner in = new Scanner(System.in);
            System.out.print("Direction: ");
            int input = in.nextInt();
            MoveExecutor moveExecutor = new MoveExecutor();
            moveExecutor.move(board, input);
            rsg.generateNextRandomTile(board);
            System.out.println();
        }
    }

}
