module com.example.chess_game {
    requires javafx.controls;
    requires javafx.fxml;



    exports chess.controllers;
    opens chess.controllers to javafx.fxml;
    exports chess;
    opens chess to javafx.fxml;
}