package chess.controllers;

import chess.pieces.ChessPiece;
import chess.pieces.Rook;

import java.util.*;

public class PieceController {
    public static List <ChessPiece> initilizeBlackPieces(){

        List <ChessPiece> BlackPieces = new ArrayList<ChessPiece>();
        Rook BlackRook1 = new Rook("black",1,1);
        Rook BlackRook2 = new Rook("black",8,1);
        System.out.println(BlackRook1.toString());
        Collections.addAll(BlackPieces,BlackRook1,BlackRook2);
        return BlackPieces;
    }
}
