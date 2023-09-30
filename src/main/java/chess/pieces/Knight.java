package chess.pieces;

import chess.enums.ChessPieceColor;

public class Knight extends ChessPiece{
    public Knight(ChessPieceColor color, int col, int row){
        this.col=col;
        this.row=row;
        this.color=color;
        this.image="src/main/resources/images/"+color+"_knight.png";
    }
    @Override
    public void movePiece() {

    }
}
