package chess.pieces;

import chess.enums.ChessPieceColor;

public class Rook extends ChessPiece{
    public Rook(ChessPieceColor color, int col, int row){
        this.col=col;
        this.row=row;
        this.color=color;
        this.image="src/main/resources/images/"+color+"_rook.png";
    }


    @Override
    public void movePiece() {

    }
}
