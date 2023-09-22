package chess;

import chess.controllers.BoardController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader root = new FXMLLoader(App.class.getResource("board-view.fxml"));
        Scene scene = new Scene(BoardController.initialize());
//        scene.getStylesheets().add(getClass() .getResource("board.css").toExternalForm());
//        stage.setTitle("akhir makayn f chess");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}