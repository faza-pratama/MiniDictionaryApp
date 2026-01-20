package org.example.minidictionaryapp.ui;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import org.example.minidictionaryapp.model.DictionaryBST;
import org.example.minidictionaryapp.util.AppLogger;

public class AddView extends GridPane {

    private final DictionaryBST bst;
    private final AppLogger logger;

    private final TextField wordField = new TextField();
    private final TextArea meaningArea = new TextArea();

    public AddView(DictionaryBST bst, AppLogger logger) {
        this.bst = bst;
        this.logger = logger;

        setPadding(new Insets(12));
        setHgap(10);
        setVgap(10);

        add(new Label("Kata:"), 0, 0);
        add(wordField, 1, 0);
        GridPane.setHgrow(wordField, Priority.ALWAYS);

        add(new Label("Arti:"), 0, 1);
        meaningArea.setPrefRowCount(6);
        meaningArea.setWrapText(true);
        add(meaningArea, 1, 1);
        GridPane.setHgrow(meaningArea, Priority.ALWAYS);

        Button addBtn = new Button("Tambah (Insert)");
        addBtn.setOnAction(e -> onAdd());

        HBox actions = new HBox(addBtn);
        actions.setSpacing(10);
        actions.setStyle("-fx-alignment: center-right;");
        add(actions, 1, 2);
    }

    private void onAdd() {
        String word = safeTrim(wordField.getText());
        String meaning = safeTrim(meaningArea.getText());

        if (word.isEmpty()) {
            warn("Validasi", "Kata tidak boleh kosong.");
            logger.log("Gagal tambah: kata kosong.");
            return;
        }
        if (meaning.isEmpty()) {
            warn("Validasi", "Arti tidak boleh kosong.");
            logger.log("Gagal tambah: arti kosong.");
            return;
        }

        boolean ok = bst.insert(word, meaning);
        if (ok) {
            info("Sukses", "Berhasil menambahkan kata.");
            logger.log("Berhasil tambah kata: \"" + word + "\"");
            wordField.clear();
            meaningArea.clear();
        } else {
            warn("Duplikat", "Kata sudah ada. Gunakan fitur Update untuk mengubah arti.");
            logger.log("Gagal tambah: kata \"" + word + "\" sudah ada (gunakan Update).");
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
