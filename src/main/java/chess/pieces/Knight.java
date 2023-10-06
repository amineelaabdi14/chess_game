package chess.pieces;

import chess.enums.ChessPieceColor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Knight extends ChessPiece{
    public Knight(ChessPieceColor color, int col, int row){
        this.col=col;
        this.row=row;
        this.color=color;
        this.image="src/main/resources/images/"+color+"_knight.png";
    }
    @Override
    public Map<String, List<int[]>> getMoveDirections() {
        int[][] deltas = {
                {1, 2},     // down-right
                {1, -2},    // down-left
                {-1, 2},    // up-right
                {-1, -2},   // up-left
                {2, 1},     // right-down
                {2, -1},    // right-up
                {-2, 1},    // left-down
                {-2, -1}    // left-up
        };

        String[] directionNames = {
                "down-right",
                "down-left",
                "up-right",
                "up-left",
                "right-down",
                "right-up",
                "left-down",
                "left-up"
        };

        Map<String, List<int[]>> directions = new HashMap<>();

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
