package org.example.minidictionaryapp.ui;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import org.example.minidictionaryapp.model.DictionaryBST;
import org.example.minidictionaryapp.util.AppLogger;

public class UpdateView extends GridPane {

    private final DictionaryBST bst;
    private final AppLogger logger;

    private final TextField wordField = new TextField();
    private final TextArea meaningArea = new TextArea();

    public UpdateView(DictionaryBST bst, AppLogger logger) {
        this.bst = bst;
        this.logger = logger;

        setPadding(new Insets(12));
        setHgap(10);
        setVgap(10);

        add(new Label("Kata yang diupdate:"), 0, 0);
        add(wordField, 1, 0);
        GridPane.setHgrow(wordField, Priority.ALWAYS);

        add(new Label("Arti baru:"), 0, 1);
        meaningArea.setPrefRowCount(6);
        meaningArea.setWrapText(true);
        add(meaningArea, 1, 1);
        GridPane.setHgrow(meaningArea, Priority.ALWAYS);

        Button updateBtn = new Button("Update");
        updateBtn.setOnAction(e -> onUpdate());

        HBox actions = new HBox(updateBtn);
        actions.setSpacing(10);
        actions.setStyle("-fx-alignment: center-right;");
        add(actions, 1, 2);
    }

    private void onUpdate() {
        String word = safeTrim(wordField.getText());
        String newMeaning = safeTrim(meaningArea.getText());

        if (word.isEmpty()) {
            warn("Validasi", "Masukkan kata yang ingin diupdate.");
            logger.log("Update gagal: kata kosong.");
            return;
        }
        if (newMeaning.isEmpty()) {
            warn("Validasi", "Arti baru tidak boleh kosong.");
            logger.log("Update gagal: arti baru kosong.");
            return;
        }

        boolean ok = bst.update(word, newMeaning);
        if (ok) {
            info("Sukses", "Berhasil update arti.");
            logger.log("Update sukses: \"" + word + "\"");
            wordField.clear();
            meaningArea.clear();
        } else {
            warn("Tidak ditemukan", "Kata tidak ditemukan. Tambahkan dulu lewat fitur Tambah Kata.");
            logger.log("Update gagal: \"" + word + "\" tidak ditemukan.");
        }
    }

    private String safeTrim(String s) {
        return s == null ? "" : s.trim();
    }

    private void info(String title, String content) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle(title);
        a.setHeaderText(null);
        a.setContentText(content);
        a.showAndWait();
    }

    private void warn(String title, String content) {
        Alert a = new Alert(Alert.AlertType.WARNING);
        a.setTitle(title);
        a.setHeaderText(null);
        a.setContentText(content);
        a.showAndWait();
    }
}
