package org.example.minidictionaryapp.util;

import javafx.scene.control.TextArea;

public class AppLogger {
    private final TextArea logArea;

    public AppLogger(TextArea logArea) {
        this.logArea = logArea;
    }

    public void log(String msg) {
        logArea.appendText("• " + msg + "\n");
    }
}
