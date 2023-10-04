package chess.pieces;

import chess.enums.ChessPieceColor;

public class Pawn extends ChessPiece{
    public boolean didMove = false;

    public void setDidMove(boolean didMove) {
        this.didMove = didMove;
    }

    public Pawn(ChessPieceColor color, int col, int row){
        this.col=col;
        this.row=row;
        this.color=color;
        this.image="src/main/resources/images/"+color+"_pawn.png";
    }

    @Override
    public int[][] getMoveDirections() {
        if (!didMove){
            int [][] directions =   {
                    {col,row+1},
                    {col,row+2}
            };
            return directions;
        }else{
            int [][] directions =   {
                    {col,row+1},
            };
            return directions;
        }
    }
    @Override
    public void movePiece() {

    }
}
