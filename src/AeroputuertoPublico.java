public class AeroputuertoPublico extends Aeroputuerto {
    private double subvencion;

    public AeroputuertoPublico(String nombre, String ciudad, String pais) {
        super(nombre, ciudad, pais);
    }

    public AeroputuertoPublico(String nombre, String ciudad, String pais, Compañia[] listaCompañias, double subvencion) {
        super(nombre, ciudad, pais, listaCompañias);
        this.subvencion = subvencion;
    }

    public AeroputuertoPublico(String nombre, String ciudad, String pais, double subvencion) {
        super(nombre, ciudad, pais);
        this.subvencion = subvencion;
    }

    public double getSubvencion() {
        return subvencion;
    }
}