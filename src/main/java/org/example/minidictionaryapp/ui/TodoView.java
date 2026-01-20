package org.example.minidictionaryapp.ui;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class TodoView extends VBox {
    public TodoView(String text) {
        setPadding(new Insets(12));
        setSpacing(8);
        getChildren().add(new Label(text));
    }
}
