package chess.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class BoardController {

    @FXML
    public  GridPane initialize() {
        GridPane chessboard = new GridPane();
        int boardSize = 8;
        int squareSize = 70;

        for(int row = 0; row < boardSize; row++) {
            for(int col = 0; col < boardSize; col++) {
                Rectangle square = new Rectangle();
                square.setWidth(squareSize);
                square.setHeight(squareSize);
                Image myImage= new Image(getClass().getResourceAsStream("/images/black_rook.png"));
                square.setFill(new ImagePattern(myImage));
                if((row + col) % 2 == 0) {
                    square.setFill(Color.web("#eeeeee"));
                } else {
                    square.setFill(Color.web("#958d95"));
                }
                chessboard.add(square, col, row);
            }
        }
        return chessboard;
    }
}