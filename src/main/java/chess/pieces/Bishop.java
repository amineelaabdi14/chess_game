package chess.pieces;

import chess.enums.ChessPieceColor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bishop extends ChessPiece {
    public Bishop(ChessPieceColor color, int col, int row){
        this.col=col;
        this.row=row;
        this.color=color;
        this.image="src/main/resources/images/"+color+"_bishop.png";
    }
    @Override
    public Map<String, List<int[]>> getMoveDirections() {
        int[][] deltas = {
                {-1, -1},   // diagonal up-left
                {-1, 1},    // diagonal up-right
                {1, -1},    // diagonal down-left
                {1, 1}      // diagonal down-right
        };

        String[] directionNames = {
                "up-left",
                "up-right",
                "down-left",
                "down-right"
        };

        Map<String, List<int[]>> directions = new HashMap<>();

        for (int i = 0; i < deltas.length; i++) {
            int newRow = this.row;
            int newCol = this.col;
            directions.put(directionNames[i], new ArrayList<>());

            while (true) {
                newRow += deltas[i][0];
                newCol += deltas[i][1];

                // Check if the new position is within the board bounds (assuming an 8x8 board)
                if (newRow < 0 || newRow >= 8 || newCol < 0 || newCol >= 8) {
                    break;  // The move is out of bounds, stop this direction
                }

                directions.get(directionNames[i]).add(new int[]{newCol, newRow});
            }
        }

        return directions;
    }

}
