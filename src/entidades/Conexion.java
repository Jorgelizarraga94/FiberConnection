package entidades;

import org.openstreetmap.gui.jmapviewer.Coordinate;

public class Conexion {
		
	private Localidad origen;
	private Localidad destino;
	private Double km;
	
	
	public Conexion(Localidad origen, Localidad destino, Double km) {
		this.origen = origen;
		this.destino = destino;
		this.km = km;
	}
	
	public Localidad getOrigen() {
        return origen;
    }

    public Localidad getDestino() {
        return destino;
    }
    
    public Coordinate getCoordenadasOrigen() {
    	return origen.getCordenadasLocalidad();
    }
    
    public Coordinate getCoordenadasDestino() {
    	return destino.getCordenadasLocalidad();
    }
    
    
    @Override
    public String toString() {
        return origen.getNombre() + " - " + destino.getNombre();
    }

}
