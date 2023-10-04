package chess.controllers;

import chess.pieces.ChessPiece;
import chess.pieces.Pawn;
import chess.services.PieceService;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

public class PieceController {
    private ChessPiece selectedPiece = null;
    private GridPane chessPiecesGrid;

    public void setSelectedPiece(ChessPiece selectedPiece) {
        this.selectedPiece = selectedPiece;
    }

    public void setChessBoardGrid(GridPane chessBoardGrid) {
        this.chessPiecesGrid = chessBoardGrid;
    }

    public void handlePieceClick(Pane element, GridPane chessPiecesGrid, ChessPiece myPiece) {
        if (myPiece == null) return;
        this.chessPiecesGrid = chessPiecesGrid;
        element.setOnMouseClicked(event -> {
            if (this.selectedPiece instanceof ChessPiece) this.resetMovementGuide();
            this.selectedPiece = myPiece;
            int clickedElementColIndex = myPiece.col;
            int clickedElementRowIndex = myPiece.row;
            int[][] nextMoves = myPiece.getMoveDirections();
            for (int[] nextMove : nextMoves) {
                Pane pane = new Pane();
                Circle circle = new Circle();
                circle.setCenterX(35);
                circle.setCenterY(35);
                circle.setRadius(10);
                circle.setStyle("-fx-fill: green");
                pane.getChildren().add(circle);
                this.handleMovePiece(element, pane);
                chessPiecesGrid.add(pane, nextMove[0], nextMove[1]);
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

    public void handleMovePiece(Pane element, Pane pane) {
        pane.setOnMouseClicked(event -> {
            int clickedCol;
            int clickedRow;
            clickedCol = chessPiecesGrid.getColumnIndex((Node) pane);
            clickedRow = chessPiecesGrid.getRowIndex((Node) pane);
            this.resetMovementGuide();
            ObservableList<Node> children = this.chessPiecesGrid.getChildren();
            Pane nodetoPane = null;
            ImageView myImage = null;
            myImage = (ImageView) element.getChildren().remove(0);
            for (Node child : children) {
                if (chessPiecesGrid.getColumnIndex((Node) child) == clickedCol &&
                        chessPiecesGrid.getRowIndex((Node) child) == clickedRow) {
                    Pane direction = (Pane) child;
                    direction.getChildren().add(myImage);
                    selectedPiece.setCol(clickedCol);
                    selectedPiece.setRow(clickedRow);
                    this.handlePieceClick(direction, chessPiecesGrid, selectedPiece);
                }
            }
            if (selectedPiece instanceof Pawn) {
                ((Pawn) selectedPiece).setDidMove(true);
            }

        });
    }
}