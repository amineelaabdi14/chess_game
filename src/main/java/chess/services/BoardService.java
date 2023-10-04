package chess.services;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class BoardService {
    public static GridPane initilizeBoard(){
        GridPane chessboard = new GridPane();
        int boardSize = 8;
        int squareSize = 70;
        for(int row = 0; row < boardSize; row++) {
            for(int col = 0; col < boardSize; col++) {
                Pane pane  = new Pane();
                pane.setMinSize(squareSize, squareSize);
                pane.setMaxSize(squareSize, squareSize);
                if((row + col) % 2 == 0) {
                    pane.setStyle("-fx-background-color: #eeeeee;");
                } else {
                    pane.setStyle("-fx-background-color: #958d95;");
                }
                chessboard.add(pane, col, row);
            }
        }
        return chessboard;
    }
}
