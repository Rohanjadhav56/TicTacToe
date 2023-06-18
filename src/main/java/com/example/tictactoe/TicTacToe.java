package com.example.tictactoe;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

public class TicTacToe extends Application {

    private Button [][]buttons = new Button[3][3];
    private Label playerXScoreLabel,playerOScoreLabel;
    private boolean playerXTurn = true;
    private int playerXScore =0;
    private int playerOScore =0;
    private BorderPane createContent(){
        BorderPane root = new BorderPane();

        //Title
        Label titleLabel = new Label("Tic Tac Toe");
        titleLabel.setStyle("-fx-font-size : 24pt; -fx-font-weight : bold;");
        root.setTop(titleLabel);
        BorderPane.setAlignment(titleLabel, Pos.CENTER);

        //Game Board
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);
        for(int i =0;i<3;i++)
        {
            for(int j = 0;j<3;j++)
            {
                Button button = new Button("");
                button.setPrefSize(100,100);
                button.setStyle("-fx-font-size : 24pt; -fx-font-weight : bold;");
                button.setOnAction(event -> buttonClicked(button));
                buttons[i][j] = button;
                gridPane.add(button,j,i);
            }
        }
        root.setCenter(gridPane);
        BorderPane.setAlignment(gridPane,Pos.CENTER);


         //Score
        HBox scoreBoard  = new HBox(20);
        scoreBoard.setAlignment(Pos.CENTER);
        playerXScoreLabel = new Label("Player X : 0");
        playerXScoreLabel.setStyle("-fx-font-size : 16pt; -fx-font-weight : bold;");
        playerOScoreLabel = new Label("Player Y : 0");
        playerOScoreLabel.setStyle("-fx-font-size : 16pt; -fx-font-weight : bold;");
        scoreBoard.getChildren().addAll(playerXScoreLabel,playerOScoreLabel);

        root.setBottom(scoreBoard);


        return root;
    }
    private void buttonClicked(Button button)
    {
        if(button.getText().equals(""))
        {
            if(playerXTurn) button.setText("X");
            else button.setText("O");

            playerXTurn = !playerXTurn;
            checkWinner();
        }
        return;
    }
    private void checkWinner()
    {
        //row
        for(int i =0;i<3;i++)
        {
            if(!buttons[i][0].getText().isEmpty() && buttons[i][0].getText().equals(buttons[i][1].getText())
            && buttons[i][1].getText().equals(buttons[i][2].getText()))
            {
                String winner = buttons[i][0].getText();
                showWinnerDialog(winner);
                updateScore(winner);
                resetBoard();
                return;
            }
        }
        //column
        for(int j =0;j<3;j++)
        {
            if(!buttons[0][j].getText().isEmpty() && buttons[0][j].getText().equals(buttons[1][j].getText())
                    && buttons[1][j].getText().equals(buttons[2][j].getText()))
            {
                String winner = buttons[0][j].getText();
                showWinnerDialog(winner);
                updateScore(winner);
                resetBoard();
                return;
            }
        }

        //diagonal

        if(!buttons[0][0].getText().isEmpty() && buttons[0][0].getText().equals(buttons[1][1].getText())
                && buttons[1][1].getText().equals(buttons[2][2].getText()))
        {
            String winner = buttons[0][0].getText();
            showWinnerDialog(winner);
            updateScore(winner);
            resetBoard();
            return;
        }

        if(!buttons[0][2].getText().isEmpty() && buttons[0][2].getText().equals(buttons[1][1].getText())
                && buttons[1][1].getText().equals(buttons[2][0].getText()))
        {
            String winner = buttons[0][2].getText();
            showWinnerDialog(winner);
            updateScore(winner);
            resetBoard();
            return;
        }
        boolean tie = true;
        for(Button row[] : buttons)
        {
            for(Button button : row)
            {
                if(button.getText().isEmpty())
                {
                    tie = false;
                    break;
                }
            }
        }
        if(tie)
        {
            showTieDialog();
            resetBoard();
        }
    }
   private void showWinnerDialog(String winner)
   {
       Alert alert = new Alert(Alert.AlertType.INFORMATION);
       alert.setTitle("Winner");
       alert.setContentText("Congratulations "+winner +"! You won the game.");
       alert.setHeaderText("");
       alert.showAndWait();
   }
    private void showTieDialog()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Tie");
        alert.setContentText("Game over ! It is a tie game.");
        alert.setHeaderText("");
        alert.showAndWait();
    }
   private void updateScore(String winner)
   {
       if(winner.equals("X"))
       {
           playerXScore++;
           playerXScoreLabel.setText("Player X : "+playerXScore);
       }
       else {
           playerOScore++;
           playerOScoreLabel.setText("Player Y :"+playerOScore);
       }
   }

   private void resetBoard(){
        for(Button row[] : buttons)
        {
            for(Button button : row)
            {
                button.setText("");
            }
        }
   }
    @Override
    public void start(Stage stage) throws IOException {

        Scene scene = new Scene(createContent());
        stage.setTitle("Tic Tac Toe");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}