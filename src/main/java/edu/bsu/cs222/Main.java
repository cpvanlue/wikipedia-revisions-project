package edu.bsu.cs222;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        String fxmlResource = "GUI.fxml";
        Parent panel;
        panel = FXMLLoader.load(getClass().getClassLoader().getResource(fxmlResource));
        Scene scene = new Scene(panel);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

    }
    public static void main(String[] args) {
        launch(args);

    }
}
