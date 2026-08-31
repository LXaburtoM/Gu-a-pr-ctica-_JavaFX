package ni.edu.uam.caferteria.modelo;

public class LoteCafe {
    private int id;
    private String productor;
    private String fecha;
    private double cantidadKg;
    private String calidad;

    public LoteCafe(int id, String productor, String fecha, double cantidadKg, String calidad) {
        this.id = id;
        this.productor = productor;
        this.fecha = fecha;
        this.cantidadKg = cantidadKg;
        this.calidad = calidad;
    }

    public int getId() {
        return id;
    }

    public String getProductor() {
        return productor;
    }

    public String getFecha() {
        return fecha;
    }

    public double getCantidadKg() {
        return cantidadKg;
    }

    public String getCalidad() {
        return calidad;
    }
}