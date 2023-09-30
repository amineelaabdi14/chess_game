package chess.services;

import chess.enums.ChessPieceColor;
import chess.pieces.ChessPiece;
import chess.pieces.King;
import chess.repositories.ChessPieceRepo;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PieceService {
    public static GridPane initilizePieces(GridPane chessBoard){
        ChessPieceColor[] myColors =ChessPieceColor.values();
        List <ChessPiece> myPieces = new ArrayList<ChessPiece>();
        for (ChessPieceColor color:myColors) {
            // Add King
            myPieces.add(ChessPieceRepo.getInstanceOfKing(color));

            // Add Queen
            myPieces.add(ChessPieceRepo.getInstanceOfQueen(color));

            // Add Pawn
            for (int i =0 ;i<8;i++){
                myPieces.add(ChessPieceRepo.getInstanceOfPawn(color,i,1));
            }

            // Add the rooks
            myPieces.add(ChessPieceRepo.getInstanceOfRook(color, 0, 0));
            myPieces.add(ChessPieceRepo.getInstanceOfRook(color, 7, 0));

            // Add the bishops
            myPieces.add(ChessPieceRepo.getInstanceOfBishop(color, 2, 0));
            myPieces.add(ChessPieceRepo.getInstanceOfBishop(color, 5, 0));

            // Add the knights
            myPieces.add(ChessPieceRepo.getInstanceOfKnight(color, 1, 0));
            myPieces.add(ChessPieceRepo.getInstanceOfKnight(color, 6, 0));
        }
        for (ChessPiece myPiece:myPieces) {
            File file = new File(myPiece.image);
            Image image = new Image(file.toURI().toString());
            ImageView myImage = new ImageView(image);
            myImage.setFitHeight(70);
            myImage.setFitWidth(70);
            chessBoard.add(myImage ,myPiece.col,myPiece.row);
        }
        return chessBoard;
    }
}
