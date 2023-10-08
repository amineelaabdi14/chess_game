package chess.services;

import chess.enums.ChessPieceColor;
import chess.pieces.ChessPiece;
import chess.pieces.King;
import chess.pieces.Knight;
import chess.pieces.Pawn;
import chess.repositories.ChessPieceRepo;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.io.File;
import java.util.*;

public class PieceService {
    private List <ChessPiece> pieces;
    public  List<ChessPiece> initilizePieces(){
        ChessPieceColor[] myColors =ChessPieceColor.values();
        List <ChessPiece> myPieces = new ArrayList<>();
        int ids =1;
        for (ChessPieceColor color:myColors) {

            // Add King
            ChessPiece king = ChessPieceRepo.getInstanceOfKing(color);
            king.setId(ids);
            ids++;
            myPieces.add(king);

            // Add Queen
            ChessPiece Queen = ChessPieceRepo.getInstanceOfQueen(color);
            Queen.setId(ids);
            ids++;
            myPieces.add(Queen);

            // Add Pawn
            for (int i =0 ;i<8;i++){
                ChessPiece Pawn = ChessPieceRepo.getInstanceOfPawn(color,i,1);
                Pawn.setId(ids);
                ids++;
                myPieces.add(Pawn);
            }

            // Add the rooks
            ChessPiece Rook1 = ChessPieceRepo.getInstanceOfRook(color, 0, 0);
            Rook1.setId(ids);
            ids++;
            myPieces.add(Rook1);

            ChessPiece Rook2 = ChessPieceRepo.getInstanceOfRook(color, 7, 0);
            Rook2.setId(ids);
            ids++;
            myPieces.add(Rook2);

            // Add the bishops
            ChessPiece Bishop1 = ChessPieceRepo.getInstanceOfBishop(color, 2, 0);
            Bishop1.setId(ids);
            ids++;
            myPieces.add(Bishop1);

            ChessPiece Bishop2 = ChessPieceRepo.getInstanceOfBishop(color, 5, 0);
            Bishop2.setId(ids);
            ids++;
            myPieces.add(Bishop2);

            // Add the knights
            ChessPiece Knight1 = ChessPieceRepo.getInstanceOfKnight(color, 1, 0);
            Knight1.setId(ids);
            ids++;
            myPieces.add(Knight1);

            ChessPiece Knight2 = ChessPieceRepo.getInstanceOfKnight(color, 6, 0);
            Knight2.setId(ids);
            ids++;
            myPieces.add(Knight2);
        }
        this.pieces=myPieces;
        return myPieces;
    }

    public  static boolean handleCaptureClick(GridPane chessPiecesGrid, ChessPiece myPiece, List<ChessPiece> pieces,Pane target){
        target.setOnMouseClicked(event -> {
            ObservableList<Node> children = chessPiecesGrid.getChildren();
//            chessPiecesGrid.getChildren().remove(target);
            for (Node child : children) {
                if (chessPiecesGrid.getColumnIndex(child) == myPiece.col && chessPiecesGrid.getRowIndex(child)  == myPiece.row) {
                    System.out.println("child is");
                    Pane childtoPan = (Pane) child;
                    System.out.println(childtoPan.getChildren().toArray().toString());
                    childtoPan.getChildren().removeIf(node -> node instanceof ImageView);
//                    chessPiecesGrid.getChildren().remove(child);
                }
            }
            pieces.stream().forEach(piece -> {
                if (piece.col == myPiece.col && piece.row == myPiece.row) {
                    pieces.remove(piece);
                }
            });
        });
        return true;
    }

}
