package org.example.minidictionaryapp.ui;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import org.example.minidictionaryapp.model.DictionaryBST;
import org.example.minidictionaryapp.util.AppLogger;

public class AppView extends BorderPane {

    private final DictionaryBST bst = new DictionaryBST();

    private final TextArea logArea = new TextArea();
    private final AppLogger logger = new AppLogger(logArea);

    public AppView() {
        setPadding(new Insets(12));

        Label header = new Label("Mini Dictionary (BST) - Implementasi Fitur 1, 2, 3 (JavaFX)");
        header.setFont(Font.font(18));
        setTop(header);
        BorderPane.setMargin(header, new Insets(0, 0, 12, 0));

        TabPane tabs = new TabPane();
        tabs.getTabs().add(new Tab("1) Tambah Kata", new AddView(bst, logger)));
        tabs.getTabs().add(new Tab("2) Cari Kata", new SearchView(bst, logger)));
        tabs.getTabs().add(new Tab("3) Update Arti", new UpdateView(bst, logger)));
        tabs.getTabs().add(new Tab("4) Delete (TODO)", new TodoView("Fitur 4 (Delete) belum dibuat.\nKelompok lain bisa implement di DictionaryBST.delete().")));
        tabs.getTabs().add(new Tab("5) Traversal (TODO)", new TodoView("Fitur 5 (Traversal) belum dibuat.\nKelompok lain bisa implement inorder/preorder/postorder di DictionaryBST.")));

        // Biar tab tidak bisa ditutup
        for (Tab t : tabs.getTabs()) t.setClosable(false);

        setCenter(tabs);

        // Log area
        logArea.setEditable(false);
        logArea.setWrapText(true);
        logArea.setPrefRowCount(4);

        TitledPane logPane = new TitledPane("Log / Status", logArea);
        logPane.setCollapsible(false);
        setBottom(logPane);
        BorderPane.setMargin(logPane, new Insets(12, 0, 0, 0));

        logger.log("Aplikasi siap. Silakan coba tambah kata terlebih dahulu.");
    }
}
