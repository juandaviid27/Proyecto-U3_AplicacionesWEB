package Model;

public class Jersey {

    private int id;
    private String equipo;
    private String temporada;
    private String talla;
    private double precio;
    private String foto;

    public Jersey(int id, String equipo, String temporada, String talla, double precio, String foto) {
        this.id = id;
        this.equipo = equipo;
        this.temporada = temporada;
        this.talla = talla;
        this.precio = precio;
        this.foto = foto;
    }

    public int getId() { return id; }
    public String getEquipo() { return equipo; }
    public String getTemporada() { return temporada; }
    public String getTalla() { return talla; }
    public double getPrecio() { return precio; }
    public String getFoto() { return foto; }
}
