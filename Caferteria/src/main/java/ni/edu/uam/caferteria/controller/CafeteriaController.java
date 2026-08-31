package ni.edu.uam.caferteria.controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;
import ni.edu.uam.caferteria.modelo.LoteCafe;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class CafeteriaController implements Initializable {
    // Componentes del formulario
    @FXML private TextField txtId;
    @FXML private TextField txtProductor;
    @FXML private DatePicker dpFecha;
    @FXML private TextField txtCantidad;
    @FXML private ComboBox<String> cbCalidad;

    // Componentes de la tabla
    @FXML private TableView<LoteCafe> tblLotes;
    @FXML private TableColumn<LoteCafe, Integer> colId;
    @FXML private TableColumn<LoteCafe, String> colProductor;
    @FXML private TableColumn<LoteCafe, String> colFecha;
    @FXML private TableColumn<LoteCafe, Double> colCantidad;
    @FXML private TableColumn<LoteCafe, String> colCalidad;

    @FXML private Label lblDetalle;

    private ObservableList<LoteCafe> listaLotes;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbCalidad.setItems(FXCollections.observableArrayList(
                "Exportación", "Estándar", "Baja Calidad"
        ));

        configurarTabla();

        listaLotes = FXCollections.observableArrayList(
                new LoteCafe(1, "Juan Pérez", "2026-08-30", 150.5, "Exportación"),
                new LoteCafe(2, "María López", "2026-08-31", 200.0, "Estándar")
        );
        tblLotes.setItems(listaLotes);

        tblLotes.setOnMouseClicked(this::mostrarDetalles);
        configurarMenuContextual();
    }

    private void configurarTabla() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colProductor.setCellValueFactory(new PropertyValueFactory<>("productor"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        colCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidadKg"));
        colCalidad.setCellValueFactory(new PropertyValueFactory<>("calidad"));
    }

    private void configurarMenuContextual() {
        MenuItem editar = new MenuItem("Editar lote");
        MenuItem eliminar = new MenuItem("Eliminar lote");

        editar.setOnAction(e -> editarLoteSeleccionado());
        eliminar.setOnAction(e -> eliminarLoteSeleccionado());

        ContextMenu menu = new ContextMenu(editar, eliminar);
        tblLotes.setContextMenu(menu);
    }

    @FXML
    private void agregarLote() {
        try {
            // 1. Validar ID vacío o inválido
            if (txtId.getText().isEmpty()) {
                lblDetalle.setText("⚠️ Error: El campo ID no puede estar vacío.");
                return;
            }
            int id = Integer.parseInt(txtId.getText());
            if (id <= 0) {
                lblDetalle.setText("⚠️ Error absurdo: El ID debe ser un número entero positivo.");
                return;
            }

            // 2. Validar Productor
            String productor = txtProductor.getText().trim();
            if (productor.isEmpty()) {
                lblDetalle.setText("⚠️ Error: Debe ingresar el nombre del productor.");
                return;
            }

            // 3. Validar Fecha (Evitar fechas futuras absurdas)
            if (dpFecha.getValue() == null) {
                lblDetalle.setText("⚠️ Error: Debe seleccionar una fecha de recepción.");
                return;
            }
            java.time.LocalDate fechaSeleccionada = dpFecha.getValue();
            java.time.LocalDate fechaActual = java.time.LocalDate.now();

            if (fechaSeleccionada.isAfter(fechaActual)) {
                lblDetalle.setText("⚠️ Error absurdo: No se puede registrar un lote con fecha futura.");
                return;
            }
            String fecha = fechaSeleccionada.toString();

            // 4. Validar Cantidad (Evitar negativos o valores irreales)
            if (txtCantidad.getText().isEmpty()) {
                lblDetalle.setText("⚠️ Error: La cantidad en Kg no puede estar vacía.");
                return;
            }
            double cantidad = Double.parseDouble(txtCantidad.getText());
            if (cantidad <= 0) {
                lblDetalle.setText("⚠️ Error absurdo: La cantidad debe ser mayor a 0 Kg.");
                return;
            }
            if (cantidad > 100000) {
                lblDetalle.setText("⚠️ Error absurdo: Cantidad exagerada para un solo lote.");
                return;
            }

            // 5. Validar ComboBox de Calidad
            String calidad = cbCalidad.getValue();
            if (calidad == null || calidad.isEmpty()) {
                lblDetalle.setText("⚠️ Error: Debe seleccionar una categoría de calidad.");
                return;
            }

            // Si pasa todas las validaciones, se crea y se añade el lote
            LoteCafe nuevoLote = new LoteCafe(id, productor, fecha, cantidad, calidad);
            listaLotes.add(nuevoLote);

            // Limpiar formulario tras el éxito
            txtId.clear();
            txtProductor.clear();
            dpFecha.setValue(null);
            txtCantidad.clear();
            cbCalidad.getSelectionModel().clearSelection();

            lblDetalle.setText("✅ ¡Lote de café registrado y validado con éxito!");

        } catch (NumberFormatException e) {
            lblDetalle.setText("⚠️ Error de formato: El ID y la Cantidad deben ser valores numéricos válidos.");
        }
    }

    private void mostrarDetalles(MouseEvent event) {
        LoteCafe lote = tblLotes.getSelectionModel().getSelectedItem();
        if (lote != null) {
            lblDetalle.setText(String.format("Detalles: ID %d | Productor: %s | Fecha: %s | %.2f Kg | Calidad: %s",
                    lote.getId(), lote.getProductor(), lote.getFecha(), lote.getCantidadKg(), lote.getCalidad()));
        }
    }

    private void editarLoteSeleccionado() {
        LoteCafe lote = tblLotes.getSelectionModel().getSelectedItem();
        if (lote != null) {
            lblDetalle.setText("Abriendo edición para: " + lote.getProductor());
        }
    }

    private void eliminarLoteSeleccionado() {
        LoteCafe lote = tblLotes.getSelectionModel().getSelectedItem();

        if (lote != null) {
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
            alerta.setTitle("Confirmar eliminación");
            alerta.setHeaderText("¿Eliminar lote de " + lote.getProductor() + "?");
            alerta.setContentText("Cantidad: " + lote.getCantidadKg() + " Kg\nEsta acción no se puede deshacer.");

            Optional<ButtonType> resultado = alerta.showAndWait();
            if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
                listaLotes.remove(lote);
                lblDetalle.setText("Lote eliminado correctamente.");
            }
        }
    }

}
