package com.alanrusnak.api2048.engine.util;

import com.alanrusnak.api2048.engine.model.Board;

public class BoardPrinter {

    public static void printBoard(Board board){
        for(int i = 0; i < 4; i++){
            System.out.print("|");
            for(int j = 0; j < 4; j++){
                System.out.print(board.getTiles()[i][j] + "|");
            }
            System.out.println();
        }
    }

}
