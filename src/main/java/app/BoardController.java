package app;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BoardController {

    @FXML
    public static GridPane initialize() {
        GridPane chessboard = new GridPane();
        int boardSize = 8;
        int squareSize = 60;

        for(int row = 0; row < boardSize; row++) {
            for(int col = 0; col < boardSize; col++) {
                Rectangle square = new Rectangle();
                square.setWidth(squareSize);
                square.setHeight(squareSize);
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