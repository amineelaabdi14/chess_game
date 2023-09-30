package chess.pieces;

import chess.enums.ChessPieceColor;

public class Pawn extends ChessPiece{
    public Pawn(ChessPieceColor color, int col, int row){
        this.col=col;
        this.row=row;
        this.color=color;
        this.image="src/main/resources/images/"+color+"_pawn.png";
    }
    @Override
    public void movePiece() {

    }
}
