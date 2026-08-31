module ni.edu.uam.caferteria {
    requires javafx.controls;
    requires javafx.fxml;


    opens ni.edu.uam.caferteria to javafx.fxml;
    exports ni.edu.uam.caferteria;
}