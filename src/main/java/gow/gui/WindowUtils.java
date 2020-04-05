package gow.gui;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class WindowUtils {
    public static void launch(URL fxmlLocation) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(fxmlLocation));
            Stage stage = new Stage();
            stage.setScene(new Scene(root, 600, 800));
            stage.show();
        } catch (IOException e) {
            throw new ExceptionWithDialog(e);
        }
    }

    public static void showPopup(Window root, Popup popup, long time, TimeUnit timeUnit) {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.submit(() -> Platform.runLater(() -> popup.show(root)));
        executor.schedule(() -> Platform.runLater(popup::hide), time, timeUnit);
        executor.shutdown();
    }
}
