package chess.pieces;

import chess.enums.ChessPieceColor;

import java.util.HashMap;
import java.util.Map;

public abstract class ChessPiece {
    public String image;
    public ChessPieceColor color;
    public int row;
    public int col;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ChessPieceColor getColor() {
        return color;
    }

    public void setColor(ChessPieceColor color) {
        this.color = color;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public abstract void movePiece();
}
