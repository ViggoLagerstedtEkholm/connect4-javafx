package com.example.connect4;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ConnectFour extends Application {
    private final int WIDTH = 600;
    private final Color[][] board = initBoard();
    private final double CELL_RADIUS = (double)WIDTH / (double)board[0].length;
    private final int HEIGHT = (int) (board.length * CELL_RADIUS);
    private int turn = 1;
    private Color player = Color.RED;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {
        stage.setTitle("CONNECT FOUR");

        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        Timeline tl = new Timeline(new KeyFrame(Duration.millis(10), e -> run(gc)));
        tl.setCycleCount(Timeline.INDEFINITE);

        stage.setScene(new Scene(new StackPane(canvas)));
        stage.setResizable(false);
        canvas.setOnMouseClicked(e -> {
            int X = (int) ((int) e.getSceneX() / CELL_RADIUS);
            int Y = (int) ((int) e.getSceneY() / CELL_RADIUS);

            if(board[Y][X] != Color.WHITE){
                showError(stage);
            }else{
                int dropToY = 0;
                for(int row = 0; row < board.length; row++){
                    if(board[row][X] == Color.WHITE){
                        dropToY = row;
                    }
                }
                if(turn % 2 == 0){
                    board[dropToY][X] = Color.RED;
                    if(GameValidator.hasWinner(board, player)){
                        showWinner(stage);
                    }
                    player = Color.GREEN;
                }else{
                    board[dropToY][X] = Color.GREEN;
                    if(GameValidator.hasWinner(board, player)){
                        showWinner(stage);
                    }
                    player = Color.RED;
                }
                turn++;
            }
        });
        stage.show();
        tl.play();
    }

    private void showError(Stage stage){
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(stage);
        VBox dialogVbox = new VBox(20);
        dialogVbox.getChildren().add(new Text("Invalid move."));
        Scene dialogScene = new Scene(dialogVbox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.show();
    }

    private void showWinner(Stage stage){
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(stage);
        VBox dialogVbox = new VBox(20);
        if(player == Color.RED){
            dialogVbox.getChildren().add(new Text("Winner was player: RED"));
        }else{
            dialogVbox.getChildren().add(new Text("Winner was player: GREEN"));
        }

        Scene dialogScene = new Scene(dialogVbox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.show();
    }

    private void run(GraphicsContext gc) {
        gc.setFill(Color.GRAY);
        gc.fillRect(0, 0, WIDTH, HEIGHT);

        gc.setFill(Color.WHITE);
        gc.setFont(Font.font(25));

        double X = 0;
        double Y = 0;
        for(int row = 0; row < board.length; row++){
            for(int col = 0; col < board[0].length; col++){
                gc.setFill(board[row][col]);
                gc.fillOval(X, Y, CELL_RADIUS, CELL_RADIUS);
                X += CELL_RADIUS;
            }
            X = 0;
            Y += CELL_RADIUS;
        }
    }

    private Color[][] initBoard(){
        Color[][] board = new Color[6][7];
        for(int row = 0; row < board.length; row++){
            for(int col = 0; col < board[0].length; col++){
                board[row][col] = Color.WHITE;
            }
        }
        return board;
    }
}