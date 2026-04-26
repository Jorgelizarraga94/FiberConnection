package entidades;

import java.util.Objects;

import javax.swing.JTextField;

import org.openstreetmap.gui.jmapviewer.Coordinate;

/**
 * Representa una localidad con nombre, provincia y coordenadas.
 */
public class Localidad {

    private String nombre;
    private String provincia;
    private double latitud;
    private double longitud;

    public Localidad(String nombre, String provincia, double latitud, double longitud) {
        this.nombre = nombre;
        this.provincia = provincia;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public Localidad() {
    }

    public Localidad(String nombre2, JTextField prov, double lat, double lon) {
    	
	}

	public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    /**
     * Devuelve un objeto Coordinate (útil para JMapViewer).
     */
    public Coordinate toCoordinate() {
        return new Coordinate(latitud, longitud);
    }

    @Override
    public String toString() {
        return "Localidad{" +
                "nombre='" + nombre + '\'' +
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
        return Objects.equals(nombre, that.nombre) &&
               Objects.equals(provincia, that.provincia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, provincia);
    }
}