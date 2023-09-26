package chess.controllers;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class BoardController {

    @FXML
    public  GridPane initialize() {
        GridPane chessboard = new GridPane();
        int boardSize = 8;
        int squareSize = 70;
        File file = new File("src/main/resources/images/black_rook.png");
        Image image = new Image(file.toURI().toString());
        ImageView myImage = new ImageView(image);
        for(int row = 0; row < boardSize; row++) {
            for(int col = 0; col < boardSize; col++) {
                StackPane stackPane = new StackPane();
//                Rectangle square= new Rectangle();
                Pane pane  = new Pane();

                pane.setMinSize(squareSize, squareSize);
                pane.setMaxSize(squareSize, squareSize);
//                square.setWidth(squareSize);
//                square.setHeight(squareSize);
//                Image myImage= new Image(getClass().getResourceAsStream("/images/black_rook.png"));
//                square.setFill(new ImagePattern(myImage));
                if((row + col) % 2 == 0) {
                    pane.setStyle("-fx-background-color: #eeeeee;");
//                    pane.setBackground(new Background(Color.web("#eeeeee"), new CornerRadii(0), new Insets(0)));
                } else {
                    pane.setStyle("-fx-background-color: #958d95;");
//                    square.setFill(Color.web("#958d95"));
                }
//                Image newImage = new Image("/src/main/resources/images/black_rook.png");

//                stackPane.getChildren().add(myImage);
//                stackPane.getChildren().add(square);
                chessboard.add(pane, col, row);

            }
        }
        chessboard.add(myImage, 1, 1);
        return chessboard;
    }
}