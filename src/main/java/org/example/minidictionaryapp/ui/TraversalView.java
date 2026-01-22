package org.example.minidictionaryapp.ui;

import javafx.geometry.Insets;
import javafx.scene.control.*;
        import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import org.example.minidictionaryapp.model.DictionaryBST;
import org.example.minidictionaryapp.util.AppLogger;

public class TraversalView extends VBox {
    private final DictionaryBST bst;
    private final AppLogger logger;
    private final TextArea displayArea = new TextArea();

    public TraversalView(DictionaryBST bst, AppLogger logger) {
        this.bst = bst;
        this.logger = logger;

        setPadding(new Insets(12));
        setSpacing(10);

        Label label = new Label("Pilih Metode Penelusuran (Traversal):");

        HBox buttons = new HBox(10);
        Button btnInorder = new Button("Inorder (A-Z)");
        Button btnPreorder = new Button("Preorder (Structure)");
        Button btnPostorder = new Button("Postorder (Cleanup)");

        btnInorder.setOnAction(e -> {
            displayArea.setText(bst.inorderList());
            logger.log("Menampilkan daftar kata urut A-Z.");
        });

        btnPreorder.setOnAction(e -> {
            displayArea.setText("Struktur Tree (Root -> Left -> Right):\n" + bst.preorderList());
            logger.log("Menampilkan struktur preorder.");
        });

        btnPostorder.setOnAction(e -> {
            displayArea.setText("Urutan Postorder (Left -> Right -> Root):\n" + bst.postorderList());
            logger.log("Menampilkan urutan postorder.");
        });

        buttons.getChildren().addAll(btnInorder, btnPreorder, btnPostorder);

        displayArea.setEditable(false);
        displayArea.setWrapText(true);
        VBox.setVgrow(displayArea, Priority.ALWAYS);

        getChildren().addAll(label, buttons, displayArea);
    }
}