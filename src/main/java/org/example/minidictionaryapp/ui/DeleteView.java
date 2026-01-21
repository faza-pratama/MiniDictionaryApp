package org.example.minidictionaryapp.ui;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import org.example.minidictionaryapp.model.DictionaryBST;
import org.example.minidictionaryapp.util.AppLogger;

public class DeleteView extends VBox {
    private final DictionaryBST bst;
    private final AppLogger logger;
    private final TextField wordField = new TextField();

    public DeleteView(DictionaryBST bst, AppLogger logger) {
        this.bst = bst;
        this.logger = logger;

        setPadding(new Insets(12));
        setSpacing(15);

        HBox top = new HBox(10);
        top.getChildren().addAll(new Label("Kata yang ingin dihapus:"), wordField);
        HBox.setHgrow(wordField, Priority.ALWAYS);

        Button deleteBtn = new Button("Hapus Kata");
        deleteBtn.setStyle("-fx-base: #ff6666;"); // Warna merah lembut untuk tombol hapus
        deleteBtn.setOnAction(e -> onDelete());

        getChildren().addAll(top, deleteBtn);
    }

    private void onDelete() {
        String word = wordField.getText().trim();
        if (word.isEmpty()) {
            warn("Validasi", "Masukkan kata yang ingin dihapus.");
            return;
        }

        boolean success = bst.delete(word);
        if (success) {
            info("Sukses", "Kata \"" + word + "\" berhasil dihapus.");
            logger.log("Hapus: \"" + word + "\" telah dibuang dari kamus.");
            wordField.clear();
        } else {
            warn("Gagal", "Kata \"" + word + "\" tidak ditemukan dalam kamus.");
            logger.log("Hapus gagal: \"" + word + "\" tidak ada.");
        }
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