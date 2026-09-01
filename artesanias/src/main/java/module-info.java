module ni.edu.uam.artesanias {
    requires javafx.controls;
    requires javafx.fxml;

    // Permite iniciar la aplicación principal
    opens ni.edu.uam.artesanias to javafx.fxml;
    exports ni.edu.uam.artesanias;

    // Da permisos para que la vista use el controlador
    opens ni.edu.uam.artesanias.controller to javafx.fxml;
    exports ni.edu.uam.artesanias.controller;

    // Da permisos para que la tabla pueda leer la clase Artesania
    opens ni.edu.uam.artesanias.modelo to javafx.base;
    exports ni.edu.uam.artesanias.modelo;
}
