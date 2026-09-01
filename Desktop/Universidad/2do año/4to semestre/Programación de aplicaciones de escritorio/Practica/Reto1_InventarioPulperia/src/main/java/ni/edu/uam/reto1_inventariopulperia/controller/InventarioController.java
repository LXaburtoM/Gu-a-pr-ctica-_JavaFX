package ni.edu.uam.reto1_inventariopulperia.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    @FXML
    private Label lblResultado;
    @FXML
    private TextField txtBuscarCodigo;

    private Map<String, Producto> inventario = new HashMap<>();

    @FXML
    void guardarProducto(ActionEvent event) {
        String codigo = txtCodigo.getText().trim();
        String nombre = txtNombre.getText().trim();
        String precioStr = txtPrecio.getText().trim();
        String cantidadStr = txtCantidad.getText().trim();

        // Validar campos vacíos
        if (codigo.isEmpty() || nombre.isEmpty() || precioStr.isEmpty() || cantidadStr.isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Campos Incompletos", "Por favor, llene todos los campos de registro.");
            return;
        }

        // Validar valores numéricos
        try {
            double precio = Double.parseDouble(precioStr);
            int cantidad = Integer.parseInt(cantidadStr);

            if (precio <= 0 || cantidad < 0) {
                mostrarAlerta(Alert.AlertType.ERROR, "Datos Inválidos", "El precio debe ser mayor a 0 y la cantidad no puede ser negativa.");
                return;
            }

            // Registrar producto en la colección
            Producto producto = new Producto(codigo, nombre, precio, cantidad);
            inventario.put(codigo, producto);

            // Mensaje flotante de éxito
            mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "¡El producto '" + nombre + "' fue guardado correctamente!");
            limpiarCamposRegistro();

        } catch (NumberFormatException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error de Formato", "El precio debe ser un número decimal y la cantidad un número entero.");
        }
    }

    @FXML
    void buscarProductoPorEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            String codigoBusqueda = txtBuscarCodigo.getText().trim();

            if (codigoBusqueda.isEmpty()) {
                if (lblResultado != null) {
                    lblResultado.setText("Ingrese un código para buscar.");
                }
                return;
            }

            if (inventario.containsKey(codigoBusqueda)) {
                Producto p = inventario.get(codigoBusqueda);

                // Muestra la información dentro del Label de abajo
                if (lblResultado != null) {
                    lblResultado.setText(String.format(
                            "Producto Encontrado:\nCódigo: %s\nNombre: %s\nPrecio: $%.2f\nExistencias: %d unidades",
                            p.getCodigo(), p.getNombre(), p.getPrecio(), p.getCantidad()
                    ));
                }
            } else {
                if (lblResultado != null) {
                    lblResultado.setText("No se encontró ningún producto con el código: " + codigoBusqueda);
                }
            }
        }
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void limpiarCamposRegistro() {
        txtCodigo.clear();
        txtNombre.clear();
        txtPrecio.clear();
        txtCantidad.clear();
    }
}
