package ni.edu.uam.artesanias.modelo;

import javafx.scene.image.ImageView;

public class Artesania {
    private String codigo;
    private String nombre;
    private double precio;
    private ImageView imagen;

    public Artesania(String codigo, String nombre, double precio, ImageView imagen) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.imagen = imagen;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public ImageView getImagen() {
        return imagen;
    }
}