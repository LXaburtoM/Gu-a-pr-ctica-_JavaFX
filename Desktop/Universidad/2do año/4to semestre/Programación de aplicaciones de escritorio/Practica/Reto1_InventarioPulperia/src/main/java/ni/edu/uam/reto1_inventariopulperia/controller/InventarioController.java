package ni.edu.uam.reto1_inventariopulperia.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import ni.edu.uam.reto1_inventariopulperia.modelos.Producto;

import java.util.HashMap;
import java.util.Map;

public class InventarioController {
    @FXML
    private TextField txtCodigo;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtPrecio;
    @FXML
    private TextField txtCantidad;
    @FXML
    private Button btnGuardar;

    private Map<String, Producto> inventario = new HashMap<>();

    @FXML
    void guardarProducto(ActionEvent event) {
        String codigo = txtCodigo.getText().trim();
        String nombre = txtNombre.getText().trim();
        String precioStr = txtPrecio.getText().trim();
        String cantidadStr = txtCantidad.getText().trim();

        // Validar campos vacíos
        if (codigo.isEmpty() || nombre.isEmpty() || precioStr.isEmpty() || cantidadStr.isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Campos Vacíos", "Por favor, complete todos los campos.");
            return;
        }

        // Validar valores numéricos
        try {
            double precio = Double.parseDouble(precioStr);
            int cantidad = Integer.parseInt(cantidadStr);

            if (precio <= 0 || cantidad < 0) {
                mostrarAlerta(Alert.AlertType.ERROR, "Error de Datos", "El precio debe ser mayor a 0 y la cantidad no puede ser negativa.");
                return;
            }

            // Registrar producto
            Producto producto = new Producto(codigo, nombre, precio, cantidad);
            inventario.put(codigo, producto);

            mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Producto registrado correctamente.");
            limpiarCampos();

        } catch (NumberFormatException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error de Formato", "El precio debe ser decimal (ej. 15.50) y la cantidad debe ser un entero.");
        }
    }

    @FXML
    void buscarProductoPorEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            String codigo = txtCodigo.getText().trim();

            if (codigo.isEmpty()) {
                mostrarAlerta(Alert.AlertType.WARNING, "Búsqueda Vacía", "Ingrese un código para buscar.");
                return;
            }

            if (inventario.containsKey(codigo)) {
                Producto p = inventario.get(codigo);
                txtNombre.setText(p.getNombre());
                txtPrecio.setText(String.valueOf(p.getPrecio()));
                txtCantidad.setText(String.valueOf(p.getCantidad()));
                mostrarAlerta(Alert.AlertType.INFORMATION, "Producto Encontrado", "Datos cargados exitosamente.");
            } else {
                mostrarAlerta(Alert.AlertType.INFORMATION, "Sin Resultados", "No se encontró ningún producto con el código: " + codigo);
            }
        }
    }

    private void limpiarCampos() {
        txtCodigo.clear();
        txtNombre.clear();
        txtPrecio.clear();
        txtCantidad.clear();
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
