package com.assignment.tictactoe.service;

import java.util.Random;

public class AiPlayer extends Player{
    private Random random;
    public AiPlayer(Board board) {
        super(board);
        this.random = new Random();
    }

    @Override
    public void move(int row, int col) {
        int randomRow, randomCol;
        do {
            randomRow = random.nextInt(3);
            randomCol = random.nextInt(3);
        } while (!board.isLegalMove(randomRow, randomCol)); {
            board.updateMove(randomRow, randomCol, Piece.O);
        }
    }
}
