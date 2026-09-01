package ni.edu.uam.artesanias;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
        @Override
        public void start(Stage stage) throws IOException {
            // Carga la vista que armamos
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("artesania-view.fxml"));

            // Ajustamos la ventana al tamaño que definimos en el FXML (750x500)
            Scene scene = new Scene(fxmlLoader.load(), 750, 500);

            stage.setTitle("Tienda de Artesanías - Catálogo");
            stage.setScene(scene);
            stage.show();
        }

        public static void main(String[] args) {
            launch();
        }
}
