module ni.edu.uam.reto1_inventariopulperia {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;


    opens ni.edu.uam.reto1_inventariopulperia to javafx.fxml;
    opens ni.edu.uam.reto1_inventariopulperia.controller to javafx.fxml;
    exports ni.edu.uam.reto1_inventariopulperia;
}