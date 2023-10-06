package chess.pieces;

import chess.enums.ChessPieceColor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Map<String, List<int[]>> getMoveDirections() {
        int[][] deltas;

        if (!didMove) {
            deltas = new int[][] {
                    {0, 1},     // Move one square forward
                    {0, 2}      // Move two squares forward (first move only)
            };
        } else {
            deltas = new int[][] {
                    {0, 1}      // Move one square forward
            };
        }

        String[] directionNames = {
                "forward",
                "forward-double"
        };

        Map<String, List<int[]>> directions = new HashMap<>();

        for (int i = 0; i < deltas.length; i++) {
            int newRow ;
            int newCol ;
            if (this.color == ChessPieceColor.BLACK) {
                 newRow = this.row + deltas[i][1];
                 newCol = this.col + deltas[i][0];
            }else {
                 newRow = this.row - deltas[i][1];
                 newCol = this.col - deltas[i][0];
            }


            // Check if the new position is within the board bounds (assuming an 8x8 board)
            if (newRow >= 0 && newRow < 8 && newCol >= 0 && newCol < 8) {
                directions.put(directionNames[i], new ArrayList<>());
                directions.get(directionNames[i]).add(new int[]{newCol, newRow});
            }
        }

        return directions;
    }

}
