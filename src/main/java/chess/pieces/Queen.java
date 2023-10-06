package chess.pieces;

import chess.enums.ChessPieceColor;

import java.util.*;

public class Queen extends ChessPiece {
    public Queen(ChessPieceColor color, int col, int row) {
        this.col = col;
        this.row = row;
        this.color = color;
        this.image = "src/main/resources/images/" + color + "_queen.png";
    }

   @Override
    public Map<String, List<int[]>> getMoveDirections() {
        int[][] directions = {
                {1, 1},
                {1, 0},
                {1, -1},
                {0, 1},
                {0, -1},
                {-1, 1},
                {-1, 0},
                {-1, -1}
        };

        Map<String, List<int[]>> possibleMoves = new HashMap<>();

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

        for (int i = 0; i < directions.length; i++) {
            int newRow = this.row;
            int newCol = this.col;
            String direction = directionNames[i];
            List<int[]> movesInDirection = new ArrayList<>();

            while (true) {
                newRow += directions[i][0];
                newCol += directions[i][1];
                if (newRow < 0 || newRow >= 8 || newCol < 0 || newCol >= 8) {
                    break;
                }
                movesInDirection.add(new int[]{newCol, newRow});
            }

            possibleMoves.put(direction, movesInDirection);
        }
        return possibleMoves;

    }


}
