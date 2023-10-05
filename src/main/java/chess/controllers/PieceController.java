package chess.controllers;

import chess.pieces.ChessPiece;
import chess.pieces.Pawn;
import chess.services.PieceService;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class PieceController {
    private ChessPiece selectedPiece = null;
    private GridPane chessPiecesGrid;

    public void setSelectedPiece(ChessPiece selectedPiece) {
        this.selectedPiece = selectedPiece;
    }

    public void setChessBoardGrid(GridPane chessBoardGrid) {
        this.chessPiecesGrid = chessBoardGrid;
    }

    public void handlePieceClick(Pane element, GridPane chessPiecesGrid, ChessPiece myPiece, List<ChessPiece> pieces) {
        if (myPiece == null) return;
        this.chessPiecesGrid = chessPiecesGrid;

        element.setOnMouseClicked(event -> {
            if (this.selectedPiece instanceof ChessPiece) this.resetMovementGuide();
            this.selectedPiece = myPiece;
            int clickedElementColIndex = myPiece.col;
            int clickedElementRowIndex = myPiece.row;
            int[][] nextMoves = myPiece.getMoveDirections();
            for (int[] nextMove : nextMoves) {
                System.out.println("test");
                if (nextMove[0] > 7 || nextMove[1] > 7 || nextMove[0] < 0 || nextMove[1] < 0) continue;
                boolean isOccupied = false;
                for (ChessPiece piece : pieces) {
                    if (piece.col == nextMove[0] && piece.row == nextMove[1]) {
                        isOccupied = true;
                        break;
                    }
                }
                if (isOccupied) {
                    System.out.println("test");
                    continue;
                } else {
                    Pane pane = new Pane();
                    Circle circle = new Circle();
                    circle.setCenterX(35);
                    circle.setCenterY(35);
                    circle.setRadius(10);
                    circle.setStyle("-fx-fill: green");
                    pane.getChildren().add(circle);
                    this.handleMovePiece(element, pane, pieces);
                    chessPiecesGrid.add(pane, nextMove[0], nextMove[1]);
                }
            }
        });
    }

    public void resetMovementGuide() {
        int[][] nextMoves = this.selectedPiece.getMoveDirections();
        ObservableList<Node> children = this.chessPiecesGrid.getChildren();
        for (Node child : children) {
            int colIndex = GridPane.getColumnIndex(child);
            int rowIndex = GridPane.getRowIndex(child);
            for (int[] nextMove : this.selectedPiece.getMoveDirections()) {
                int targetCol = nextMove[0];
                int targetRow = nextMove[1];
                // primitive type lover oui
                if (colIndex == targetCol && rowIndex == targetRow) {
                    Pane pane = (Pane) child;
                    pane.getChildren().removeIf(node -> node instanceof Circle);
                }
            }
        }
    }

    public void handleMovePiece(Pane element, Pane pane, List<ChessPiece> pieces) {
        pane.setOnMouseClicked(event -> {
            int clickedCol, clickedRow;
            clickedCol = chessPiecesGrid.getColumnIndex((Node) pane);
            clickedRow = chessPiecesGrid.getRowIndex((Node) pane);
            this.resetMovementGuide();
            ObservableList<Node> children = this.chessPiecesGrid.getChildren();
            Pane nodetoPane = null;
            ImageView myImage = null;
            myImage = (ImageView) element.getChildren().remove(0);
            chessPiecesGrid.add(new Pane(), selectedPiece.col, selectedPiece.row);
            chessPiecesGrid.getChildren().remove(element);
            for (Node child : children) {
                if (chessPiecesGrid.getColumnIndex((Node) child) == clickedCol &&
                        chessPiecesGrid.getRowIndex((Node) child) == clickedRow) {
                    Pane direction = (Pane) child;
                    direction.getChildren().add(myImage);
                    selectedPiece.setCol(clickedCol);
                    selectedPiece.setRow(clickedRow);
                    this.handlePieceClick(direction, chessPiecesGrid, selectedPiece, pieces);
                }
            }
            if (selectedPiece instanceof Pawn) {
                ((Pawn) selectedPiece).setDidMove(true);
            }

        });
    }
}