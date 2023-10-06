package chess.pieces;

import chess.enums.ChessPieceColor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class King extends ChessPiece {
    public King(ChessPieceColor color, int col, int row){
        this.col=col;
        this.row=row;
        this.color=color;
        this.image="src/main/resources/images/"+color+"_king.png";
    }
    @Override
    public Map<String, List<int[]>> getMoveDirections() {
        int[][] deltas = {
                {1, 1},     // diagonal down-right
                {1, 0},     // down
                {1, -1},    // diagonal down-left
                {0, 1},     // right
                {0, -1},    // left
                {-1, 1},    // diagonal up-right
                {-1, 0},    // up
                {-1, -1}    // diagonal up-left
        };

        String[] directionNames = {
                "down-right",
                "down",
                "down-left",
                "right",
                "left",
                "up-right",
                "up",
                "up-left"
        };

        Map<String, List    <int[]>> directions = new HashMap<>();

        for (int i = 0; i < deltas.length; i++) {
            int newRow = this.row + deltas[i][0];
            int newCol = this.col + deltas[i][1];

            // Check if the new position is within the board bounds (assuming an 8x8 board)
            if (newRow >= 0 && newRow < 8 && newCol >= 0 && newCol < 8) {
                directions.put(directionNames[i], new ArrayList<>());
                directions.get(directionNames[i]).add(new int[]{newCol, newRow});
            }
        }

        return directions;
    }

}
