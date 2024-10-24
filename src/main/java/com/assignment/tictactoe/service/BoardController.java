package com.assignment.tictactoe.service;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogEvent;
import javafx.scene.layout.GridPane;

public class BoardController implements BoardUI{
    private Player humanPlayer;
    private Player aiPlayer;
    private Board board;

    @FXML
    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;

    private Button[][] buttons;

    @FXML
    public void initialize() {
        buttons = new Button[][] {
                {btn1,btn2,btn3},
                {btn4,btn5,btn6},
                {btn7,btn8,btn9}
        };

        board = new BoardImpl(this);
        humanPlayer = new HumanPlayer(board);
        aiPlayer = new AiPlayer(board);
    }

    public void resetBoard() {
        board.resetBoard();

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                Button button = buttons[row][col];

                button.setText("");
                button.setDisable(false);;
            }
        }
    }

    @FXML
    void btnOnAction(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        int row = GridPane.getRowIndex(clickedButton);
        int col = GridPane.getColumnIndex(clickedButton);

        if (!clickedButton.isDisable()) {
            humanMove(row, col);
            board.printBoard();
        }
    }

    private void humanMove(int row, int col) {
        humanPlayer.move(row, col);
        Winner winner = board.checkWinner();

        if (winner != null) {
            if (winner.getPiece() == Piece.EMPTY) {
                notifyTie();
            } else {
                notifyWinner(winner);
            }
        } else if (board.isBoardFull()) {
            notifyTie();
        } else {
            aiMove();
        }
    }

    private void aiMove() {
        aiPlayer.move(0, 0);
        Winner winner = board.checkWinner();

        if (winner != null) {
            notifyWinner(winner);
        } else if (board.isBoardFull()) {
            notifyTie();
        }
    }

    @Override
    public void update(int row, int col, boolean isHuman) {
        String text = isHuman ? "X" : "O";

        Button button = buttons[row][col];

        if (button.getText().isEmpty()) {
            button.setText(text);
            button.setDisable(true);
        }
//        if (row == 0 && col == 0) btn1.setText(text);
//        else if (row == 0 && col == 1) btn2.setText(text);
//        else if (row == 0 && col == 2) btn3.setText(text);
//        else if (row == 1 && col == 0) btn4.setText(text);
//        else if (row == 1 && col == 1) btn5.setText(text);
//        else if (row == 1 && col == 2) btn6.setText(text);
//        else if (row == 2 && col == 0) btn7.setText(text);
//        else if (row == 2 && col == 1) btn8.setText(text);
//        else if (row == 2 && col == 2) btn9.setText(text);

    }

    @Override
    public void notifyWinner(Winner winner) {
        System.out.println(winner.getPiece() + " is Winner");
        showAlert(winner.getPiece() + " is Winner");
    }

    private void notifyTie() {
        System.out.println("Tie");
        showAlert("Tie");
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message);
        alert.setOnCloseRequest((DialogEvent event) -> resetBoard());
        alert.showAndWait();
    }
}
