package entidades;

import java.util.Objects;

import javax.swing.JTextField;

import org.openstreetmap.gui.jmapviewer.Coordinate;

/**
 * Representa una localidad con nombre, provincia y coordenadas.
 */
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

    /**
     * Devuelve un objeto Coordinate (útil para JMapViewer).
     */
    public Coordinate getCordenadasLocalidad() {
        return new Coordinate(latitud, longitud);
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