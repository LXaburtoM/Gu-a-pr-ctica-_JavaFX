module ni.edu.uam.caferteria {
    requires javafx.controls;
    requires javafx.fxml;

    // Permite a JavaFX arrancar la aplicación principal
    opens ni.edu.uam.caferteria to javafx.fxml;
    exports ni.edu.uam.caferteria;

    // Permite al FXML inyectar los botones y cajas de texto en tu controlador
    opens ni.edu.uam.caferteria.controller to javafx.fxml;
    exports ni.edu.uam.caferteria.controller;

    // Permite a la TableView (javafx.base) leer los datos de tu clase LoteCafe
    opens ni.edu.uam.caferteria.modelo to javafx.base;
    exports ni.edu.uam.caferteria.modelo;
}