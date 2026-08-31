module ni.edu.uam.artesanias {
    requires javafx.controls;
    requires javafx.fxml;


    opens ni.edu.uam.artesanias to javafx.fxml;
    exports ni.edu.uam.artesanias;
}