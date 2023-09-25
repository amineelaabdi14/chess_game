package chess.pieces;

public abstract class ChessPiece {
    public String image;
    public String color;
    public int col;

    public void setCol(int col) {
        this.col = col;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public String getImage() {
        return image;
    }

    public String getColor() {
        return color;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public int row;

    public abstract void movePiece();
}
