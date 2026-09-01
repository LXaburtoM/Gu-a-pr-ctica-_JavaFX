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
    @FXML
    private void guardarProducto() {
        try {
            String codigo = txtCodigo.getText();
            String nombre = txtNombre.getText();

            // Validación básica para explicar en clase
            if (codigo.isEmpty() || nombre.isEmpty() || txtPrecio.getText().isEmpty()) {
                lblMensaje.setText("Por favor, llene todos los campos.");
                return;
            }

            double precio = Double.parseDouble(txtPrecio.getText());

            // Por simplicidad, creamos un contenedor de imagen vacío.
            // (Para explicar que la tabla soporta imágenes sin complicar el código)
            ImageView imagenVacia = new ImageView();
            imagenVacia.setFitWidth(40);
            imagenVacia.setFitHeight(40);

            // Crear el objeto y agregarlo a la tabla
            Artesania nueva = new Artesania(codigo, nombre, precio, imagenVacia);
            listaArtesanias.add(nueva);

            lblMensaje.setText("Artesanía guardada con éxito.");
            nuevoProducto(); // Limpiamos los campos

        } catch (NumberFormatException e) {
            lblMensaje.setText("Error: El precio debe ser un número.");
        }
    }
    @FXML
    private void nuevoProducto() {
        txtCodigo.clear();
        txtNombre.clear();
        txtPrecio.clear();
    }
}
