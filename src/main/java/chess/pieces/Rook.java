package chess.pieces;

public class Rook extends ChessPiece{
    public Rook(String color,int col, int row){
        this.col=col;
        this.row=row;
        this.color=color;
        this.image= "//images/black_rook.png";
    }


    @Override
    public void movePiece() {

    }
}
