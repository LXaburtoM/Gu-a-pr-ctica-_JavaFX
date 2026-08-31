package ni.edu.uam.caferteria;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("cafeteria-view.fxml"));
        // Ajustamos la ventana a 850x550 para que el formulario y la tabla entren bien
        Scene scene = new Scene(fxmlLoader.load(), 850, 550);
        stage.setTitle("Sistema de Recepción de Lotes de Café");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}