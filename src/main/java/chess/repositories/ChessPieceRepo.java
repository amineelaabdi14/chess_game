package chess.repositories;

import chess.enums.ChessPieceColor;
import chess.pieces.*;

public class ChessPieceRepo {
    public static King getInstanceOfKing(ChessPieceColor color){
        if (ChessPieceColor.WHITE.equals(color)){
            return  new King(ChessPieceColor.WHITE,4,7);
        }
        return  new King(ChessPieceColor.BLACK,4,0);
    }
    public static Queen getInstanceOfQueen(ChessPieceColor color){
        if (ChessPieceColor.WHITE.equals(color)){
            return  new Queen(ChessPieceColor.WHITE,3,7);
        }
        return  new Queen(ChessPieceColor.BLACK,3,0);
    }
    public static Pawn getInstanceOfPawn(ChessPieceColor color,int col,int row){
        if (ChessPieceColor.WHITE.equals(color)){
            return  new Pawn(ChessPieceColor.WHITE,7-col,7-row);
        }
        return  new Pawn(ChessPieceColor.BLACK,col,row);
    }
    public static Rook getInstanceOfRook(ChessPieceColor color, int col, int row) {
        if (ChessPieceColor.WHITE.equals(color)) {
            return new Rook(ChessPieceColor.WHITE, 7 - col, 7 - row);
        }
        return new Rook(ChessPieceColor.BLACK, col, row);
    }

    public static Bishop getInstanceOfBishop(ChessPieceColor color, int col, int row) {
        if (ChessPieceColor.WHITE.equals(color)) {
            return new Bishop(ChessPieceColor.WHITE, 7 - col, 7 - row);
        }
        return new Bishop(ChessPieceColor.BLACK, col, row);
    }

    public static Knight getInstanceOfKnight(ChessPieceColor color, int col, int row) {
        if (ChessPieceColor.WHITE.equals(color)) {
            return new Knight(ChessPieceColor.WHITE, 7 - col, 7 - row);
        }
        return new Knight(ChessPieceColor.BLACK, col, row);
    }
}
