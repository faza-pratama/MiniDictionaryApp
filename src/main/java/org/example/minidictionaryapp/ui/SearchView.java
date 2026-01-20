package org.example.minidictionaryapp.ui;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import org.example.minidictionaryapp.model.DictionaryBST;
import org.example.minidictionaryapp.util.AppLogger;

public class SearchView extends VBox {

    private final DictionaryBST bst;
    private final AppLogger logger;

    private final TextField wordField = new TextField();
    private final TextArea resultArea = new TextArea();

    public SearchView(DictionaryBST bst, AppLogger logger) {
        this.bst = bst;
        this.logger = logger;

        setPadding(new Insets(12));
        setSpacing(10);

        HBox top = new HBox(10);
        top.getChildren().addAll(new Label("Kata yang dicari:"), wordField);

        Button searchBtn = new Button("Cari (Search)");
        searchBtn.setOnAction(e -> onSearch());
        top.getChildren().add(searchBtn);

        HBox.setHgrow(wordField, Priority.ALWAYS);
        getChildren().add(top);

        resultArea.setEditable(false);
        resultArea.setWrapText(true);

        TitledPane resultPane = new TitledPane("Hasil", resultArea);
        resultPane.setCollapsible(false);
        getChildren().add(resultPane);
    }

    private void onSearch() {
        String word = safeTrim(wordField.getText());
        if (word.isEmpty()) {
            warn("Validasi", "Masukkan kata yang ingin dicari.");
            logger.log("Cari gagal: kata kosong.");
            return;
        }

        String meaning = bst.search(word);
        if (meaning == null) {
            resultArea.setText("Kata \"" + word + "\" tidak ditemukan.");
            logger.log("Cari: \"" + word + "\" tidak ditemukan.");
        } else {
            resultArea.setText("Kata: " + word + "\nArti: " + meaning);
            logger.log("Cari: \"" + word + "\" ditemukan.");
        }
    }

    private String safeTrim(String s) {
        return s == null ? "" : s.trim();
    }

    private void warn(String title, String content) {
        Alert a = new Alert(Alert.AlertType.WARNING);
        a.setTitle(title);
        a.setHeaderText(null);
        a.setContentText(content);
        a.showAndWait();
    }
}
