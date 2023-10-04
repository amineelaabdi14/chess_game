package chess.pieces;

import chess.enums.ChessPieceColor;

public class Queen extends ChessPiece {
    public Queen(ChessPieceColor color, int col, int row){
        this.col=col;
        this.row=row;
        this.color=color;
        this.image="src/main/resources/images/"+color+"_queen.png";
    }
    @Override
    public int[][] getMoveDirections() {
        int [][] directions =  {{0,1}};
        return directions;
    }
    @Override
    public void movePiece() {

    }
}
