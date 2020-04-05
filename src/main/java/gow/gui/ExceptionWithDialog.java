package gow.gui;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionWithDialog extends RuntimeException {

    private static void showError(Exception e) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(e.getClass().getSimpleName());
            alert.setHeaderText(e.getMessage());

            VBox dialogPaneContent = new VBox();

            Label label = new Label("Stack Trace:");

            String stackTrace = getStackTrace(e);
            TextArea textArea = new TextArea();
            textArea.setText(stackTrace);

            dialogPaneContent.getChildren().addAll(label, textArea);

            // Set content for Dialog Pane
            alert.getDialogPane().setContent(dialogPaneContent);

            alert.showAndWait();
        });
    }

    private static String getStackTrace(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString();
    }

    public ExceptionWithDialog() {
        super();
        showError(this);
    }

    public ExceptionWithDialog(String message) {
        super(message);
        showError(this);
    }

    public ExceptionWithDialog(String message, Throwable cause) {
        super(message, cause);
        showError(this);
    }

    public ExceptionWithDialog(Throwable cause) {
        super(cause);
        showError(this);
    }
}
