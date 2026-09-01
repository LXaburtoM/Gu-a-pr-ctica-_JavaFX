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

    // Buscador de la ToolBar
    @FXML private TextField txtBuscar;

    // Componentes de la tabla
    @FXML private TableView<Artesania> tblArtesanias;
    @FXML private TableColumn<Artesania, ImageView> colImagen;
    @FXML private TableColumn<Artesania, String> colCodigo;
    @FXML private TableColumn<Artesania, String> colNombre;
    @FXML private TableColumn<Artesania, Double> colPrecio;

    // Listas de datos
    private ObservableList<Artesania> listaArtesanias;
    private FilteredList<Artesania> listaFiltrada;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // 1. Vincular las columnas con los atributos de la clase Artesania
        colImagen.setCellValueFactory(new PropertyValueFactory<>("imagen"));
        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));

        // 2. Inicializar la lista principal
        listaArtesanias = FXCollections.observableArrayList();

        // 3. Vincular la tabla con la lista filtrada
        listaFiltrada = new FilteredList<>(listaArtesanias, b -> true);
        tblArtesanias.setItems(listaFiltrada);
    }
}
