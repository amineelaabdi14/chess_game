package chess.pieces;

import chess.enums.ChessPieceColor;

public class King extends ChessPiece {
    public King(ChessPieceColor color, int col, int row){
        this.col=col;
        this.row=row;
        this.color=color;
        this.image="src/main/resources/images/"+color+"_king.png";
    }
    @Override
    public void movePiece() {

    }
}
