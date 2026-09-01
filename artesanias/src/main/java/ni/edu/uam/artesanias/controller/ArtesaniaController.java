package ni.edu.uam.artesanias.controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import ni.edu.uam.artesanias.modelo.Artesania;

import java.net.URL;
import java.util.ResourceBundle;
public class ArtesaniaController implements Initializable {
    // Componentes del formulario
    @FXML private TextField txtCodigo;
    @FXML private TextField txtNombre;
    @FXML private TextField txtPrecio;
    @FXML private Label lblMensaje;
}
