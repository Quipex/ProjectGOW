package gow;

import gow.utils.Properties;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResourceAsStream("/fxml/MainWindow.fxml"));
        primaryStage.setTitle(Properties.getString("app.title") + " v" + Properties.getString("app.version"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
