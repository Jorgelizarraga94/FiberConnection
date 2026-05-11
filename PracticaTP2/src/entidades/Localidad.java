package entidades;
import java.util.Objects;
import javax.swing.JTextField;
import org.openstreetmap.gui.jmapviewer.Coordinate;
public class Localidad {

    private String nombreLocalidad;
    private String provincia;
    private double latitud;
    private double longitud;

    public Localidad(double latitud, double longitud, String provincia, String nombre) {
    	this.latitud = latitud;
    	this.longitud = longitud;
        this.nombreLocalidad = nombre;
        this.provincia = provincia;
    }
	public String getNombre() {
        return nombreLocalidad;
    }
    public void setNombre(String nombre) {
        this.nombreLocalidad = nombre;
    }
    public String getProvincia() {
        return provincia;
    }
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
    public double getLatitud() {
        return latitud;
    }
    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }
    public double getLongitud() {
        return longitud;
    }
    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }
    public Coordinate getCordenadasLocalidad() {
        return new Coordinate(latitud, longitud);
    }
    public double distanciaEntreDosPuntos(Localidad otra) {
        final int R = 6371; 
        double dLat = Math.toRadians(otra.getLatitud() - this.latitud);
        double dLon = Math.toRadians(otra.getLongitud() - this.longitud);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(Math.toRadians(this.latitud)) * Math.cos(Math.toRadians(otra.getLatitud())) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }
    @Override
    public String toString() {
        return "Localidad{" +
                "nombre='" + nombreLocalidad + '\'' +
                ", provincia='" + provincia + '\'' +
                ", latitud=" + latitud +
                ", longitud=" + longitud +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Localidad that = (Localidad) o;
        return Objects.equals(nombreLocalidad, that.nombreLocalidad) &&
               Objects.equals(provincia, that.provincia);
    }
    @Override
    public int hashCode() {
        return Objects.hash(nombreLocalidad, provincia);
    }
}