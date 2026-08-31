module ni.edu.uam.reto1_inventariopulperia {
    requires javafx.controls;
    requires javafx.fxml;


    opens ni.edu.uam.reto1_inventariopulperia to javafx.fxml;
    exports ni.edu.uam.reto1_inventariopulperia;
}