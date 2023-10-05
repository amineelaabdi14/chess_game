package chess.controllers;

import chess.pieces.ChessPiece;
import chess.services.*;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.io.File;
import java.util.List;

public class GameController {

    public Group startGame() {
        Group game= new Group();
        GridPane chessBoardGrid = BoardService.initilizeBoard();
        PieceService pieceService = new PieceService() ;
        List<ChessPiece> myPieces = pieceService.initilizePieces();
        GridPane chessPiecesGrid = new GridPane();
        PieceController pieceController = new PieceController();
        int boardSize = 8;
        int squareSize = 70;
        for(int row = 0; row < boardSize; row++) {
            for(int col = 0; col < boardSize; col++) {
                boolean notFound = true ;
                for (ChessPiece myPiece:myPieces) {
                    if (col == myPiece.col && row == myPiece.row) {
                        Pane pane = new Pane();
                        File file = new File(myPiece.image);
                        Image image = new Image(file.toURI().toString());
                        ImageView myImage = new ImageView(image);
                        myImage.setId(String.valueOf(myPiece.id));
                        myImage.setFitHeight(squareSize);
                        myImage.setFitWidth(squareSize);
                        pane.getChildren().add(myImage);
                        chessPiecesGrid.add(pane, myPiece.col, myPiece.row);
                        pieceController.handlePieceClick(pane, chessPiecesGrid,myPiece ,myPieces);
                        notFound=false;
                    }
                }
                if (notFound){
                    Pane pane = new Pane();
                    pane.setMinSize(squareSize, squareSize);
                    pane.setMaxSize(squareSize, squareSize);
                    chessPiecesGrid.add(pane, col, row);
                    pieceController.handlePieceClick(pane, chessPiecesGrid,null,myPieces);

                }
            }
        }
        game.getChildren().addAll(chessBoardGrid,chessPiecesGrid);
        return game;
    }

}