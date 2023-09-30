package chess.controllers;

import chess.services.BoardService;
import chess.services.PieceService;
import javafx.scene.layout.*;

public class GameController {

    public static GridPane startGame() {
        GridPane chessBoard = BoardService.initilizeBoard();
        chessBoard = PieceService.initilizePieces(chessBoard);
        return chessBoard;
    }
}