package com.assignment.tictactoe.service;

public class BoardImpl implements Board{
    private Piece[][] pieces;
    private BoardUI boardUI;

    public BoardImpl(BoardUI boardUI) {
        this.boardUI = boardUI;
        initializeBoard();
    }

    @Override
    public BoardUI getBoardUI() {
        return this.boardUI;
    }

    @Override
    public void initializeBoard() {
        pieces = new Piece[3][3];
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                pieces[row][col] = Piece.EMPTY;
            }
        }
    }

    @Override
    public boolean isLegalMove(int row, int col) {
        if (row < 0 || row > 3 || col < 0 || col > 3) {
            return false;
        } if (pieces[row][col] != Piece.EMPTY) {
            return false;
        }
        return true;
    }

    @Override
    public void updateMove(int row, int col, Piece piece) {
        if (isLegalMove(row, col)) {
            pieces[row][col] = piece;
            boardUI.update(row, col, piece == Piece.X);
        }
    }

    @Override
    public Winner checkWinner() {
        for (int row = 0; row < 3; row++) {
            if (pieces[row][0] != Piece.EMPTY && pieces[row][0] == pieces[row][1] && pieces[row][1] == pieces[row][2]) {
                return new Winner(pieces[row][0], row, 0, row, 1, row, 2);
            }
        }

        for (int col = 0; col < 3; col++) {
            if (pieces[0][col] != Piece.EMPTY && pieces[0][col] == pieces[1][col] && pieces[1][col] == pieces[2][col]) {
                return new Winner(pieces[0][col], 0, col, 1, col, 2, col);
            }
        }

        if (pieces[0][0] != Piece.EMPTY && pieces[0][0] == pieces[1][1] && pieces[1][1] == pieces[2][2]) {
            return new Winner(pieces[0][0], 0, 0, 1, 1, 2, 2);
        }

        if (pieces[0][2] != Piece.EMPTY && pieces[0][2] == pieces[1][1] && pieces[1][1] == pieces[2][0]) {
            return new Winner(pieces[0][2], 0, 2, 1, 1, 2, 0);
        }

        if (isBoardFull()) {
            return new Winner(Piece.EMPTY, -1, -1, -1, -1, -1, -1);
        }
        return null;
    }

    @Override
    public void printBoard() {
        for(int i =0; i < pieces.length; i++) {
            for(int j = 0; j < pieces[i].length; j++) {
                System.out.print(pieces[i][j] + " ");
            }
            System.out.println();
        }
    }

    @Override
    public boolean isBoardFull() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (pieces[row][col] == Piece.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void resetBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                pieces[row][col] = Piece.EMPTY;
            }
        }
    }
}
