package org.example.minidictionaryapp.app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.minidictionaryapp.ui.AppView;

public class MiniDictionaryApp extends Application {

    @Override
    public void start(Stage stage) {
        AppView root = new AppView();
        Scene scene = new Scene(root, 900, 600);

        stage.setTitle("Mini Dictionary (BST) - Fitur 1/2/3 (JavaFX)");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
