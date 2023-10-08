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
import javafx.scene.shape.Rectangle;

import java.util.*;

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
            Map<String, List<int[]>> moveDirections = myPiece.getMoveDirections();
            for (Map.Entry<String, List<int[]>> entry : moveDirections.entrySet()) {
                String direction = entry.getKey();
                List<int[]> possibleMoves = entry.getValue();

                for (int[] nextMove : possibleMoves) {
                    int nextRow = nextMove[1];
                    int nextCol = nextMove[0];
                    if (nextRow > 7 || nextCol > 7 || nextRow < 0 || nextCol < 0) continue;

                    boolean isOccupied = false;
                    boolean isOccupiedByEnemy = false;
                    for (ChessPiece piece : pieces) {
                        if (piece.col == nextCol && piece.row == nextRow) {
                            if (piece.color != myPiece.color) {
                                isOccupiedByEnemy = true;
                                Pane pane = new Pane();
                                Rectangle rectangle = new Rectangle();
                                rectangle.setWidth(70);
                                rectangle.setHeight(70);
                                rectangle.setStyle("-fx-fill: rgba(255,231,118,0.49)");
                                pane.getChildren().add(rectangle);
                                pane.setId("captureGuide");
                                chessPiecesGrid.add(pane, nextCol, nextRow);
                                this.handlePieceCapture(element,pane, chessPiecesGrid, piece, pieces);
                            }
                            isOccupied = true;
                            break;
                        }
                    }

                    if (!isOccupied) {
                        Pane pane = new Pane();
                        Circle circle = new Circle();
                        circle.setCenterX(35);
                        circle.setCenterY(35);
                        circle.setRadius(10);
                        circle.setStyle("-fx-fill: #15db15");
                        pane.getChildren().add(circle);
                        if (!isOccupiedByEnemy) this.handleMovePiece(element, pane, pieces);
                        chessPiecesGrid.add(pane, nextCol, nextRow);
                    } else {
                        break;
                    }
                }
            }
        });
    }

    public boolean handlePieceCapture(Pane selectedPiece,Pane clickedPane, GridPane chessPiecesGrid, ChessPiece myPiece, List<ChessPiece> pieces) {
        clickedPane.setOnMouseClicked(event -> {
            ObservableList<Node> children = chessPiecesGrid.getChildren();
            chessPiecesGrid.getChildren().remove(clickedPane);
            for (Node child : children) {
                if (chessPiecesGrid.getColumnIndex(clickedPane) == chessPiecesGrid.getColumnIndex(child) && chessPiecesGrid.getRowIndex(clickedPane) == chessPiecesGrid.getRowIndex(child)) {
                    Pane childtoPan = (Pane) child;
                    Pane paneToAdd = new Pane();
                    paneToAdd.setMinWidth(70);
                    paneToAdd.setMinHeight(70);
                    paneToAdd.setMaxHeight(70);
                    paneToAdd.setMaxWidth(70);
                    chessPiecesGrid.add(paneToAdd, chessPiecesGrid.getColumnIndex(clickedPane), chessPiecesGrid.getRowIndex(clickedPane));
                    childtoPan.getChildren().remove(0);
                    this.movePiece(selectedPiece, paneToAdd, pieces);
                    pieces.remove(myPiece);
                    break;
                }
            }
        });
        return true;
    }

    public void resetMovementGuide() {
        Map<String, List<int[]>> moveDirections = this.selectedPiece.getMoveDirections();
        ObservableList<Node> children = this.chessPiecesGrid.getChildren();
        chessPiecesGrid.getChildren().removeAll(chessPiecesGrid.lookupAll("#captureGuide"));
        for (Node child : children) {
            int colIndex = GridPane.getColumnIndex(child);
            int rowIndex = GridPane.getRowIndex(child);
            if (colIndex != -1 && rowIndex != -1) {
                for (List<int[]> possibleMoves : moveDirections.values()) {
                    for (int[] nextMove : possibleMoves) {

                        int targetCol = nextMove[0];
                        int targetRow = nextMove[1];

                        if (colIndex == targetCol && rowIndex == targetRow) {

                            Pane pane = (Pane) child;
                            pane.getChildren().removeIf(node -> !(node instanceof ImageView));}
                    }
                }
            }
        }
    }

    public void handleMovePiece(Pane element, Pane pane, List<ChessPiece> pieces) {
        pane.setOnMouseClicked(event -> {
            this.movePiece(element, pane, pieces);
        });
    }

    public boolean movePiece(Pane element, Pane pane, List<ChessPiece> pieces) {
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
            Pane direction = (Pane) child;
            System.out.println(direction.getChildren().size());

            if (chessPiecesGrid.getColumnIndex(child) == clickedCol &&
                    chessPiecesGrid.getRowIndex(child) == clickedRow) {
                 direction = (Pane) child;
                direction.getChildren().add(myImage);
                selectedPiece.setCol(clickedCol);
                selectedPiece.setRow(clickedRow);
                this.handlePieceClick(direction, chessPiecesGrid, selectedPiece, pieces);
            }
        }
        if (selectedPiece instanceof Pawn) {
            ((Pawn) selectedPiece).setDidMove(true);
        }

        return true;
    }
}