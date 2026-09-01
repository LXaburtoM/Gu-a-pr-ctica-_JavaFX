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

        if (codigo.isEmpty() || nombre.isEmpty() || precioStr.isEmpty() || cantidadStr.isEmpty()) {
            lblResultado.setText("Error: Todos los campos de registro son obligatorios.");
            return;
        }

        try {
            double precio = Double.parseDouble(precioStr);
            int cantidad = Integer.parseInt(cantidadStr);

            if (precio <= 0 || cantidad < 0) {
                lblResultado.setText("Error: Precio debe ser > 0 y cantidad >= 0.");
                return;
            }

            Producto producto = new Producto(codigo, nombre, precio, cantidad);
            inventario.put(codigo, producto);

            lblResultado.setText("¡Producto '" + nombre + "' registrado con éxito!");
            limpiarCamposRegistro();

        } catch (NumberFormatException e) {
            lblResultado.setText("Error: Precio debe ser decimal y cantidad un entero.");
        }
    }

    @FXML
    void buscarProductoPorEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            String codigoBusqueda = txtBuscarCodigo.getText().trim();

            if (codigoBusqueda.isEmpty()) {
                lblResultado.setText("Error: Ingrese un código en la casilla de búsqueda.");
                return;
            }

            if (inventario.containsKey(codigoBusqueda)) {
                Producto p = inventario.get(codigoBusqueda);

                // Muestra la información del producto estructurada en el Label
                lblResultado.setText(String.format(
                        "Producto Encontrado:\nCódigo: %s\nNombre: %s\nPrecio: $%.2f\nExistencias: %d unidades",
                        p.getCodigo(), p.getNombre(), p.getPrecio(), p.getCantidad()
                ));
            } else {
                lblResultado.setText("No se encontró ningún producto con el código: " + codigoBusqueda);
            }
        }
    }

    private void limpiarCamposRegistro() {
        txtCodigo.clear();
        txtNombre.clear();
        txtPrecio.clear();
        txtCantidad.clear();
    }
}
