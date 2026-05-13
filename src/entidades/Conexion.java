package entidades;

import org.openstreetmap.gui.jmapviewer.Coordinate;

public class Conexion {
		
	private Localidad origen;
	private Localidad destino;
	private Double km;
	/*DATOS CONSTANTES, A FUTURO DEBERIA DE SETEARLO EL USUARIO MANAGER 
	A TRAVES DE LA GUI*/
	private final Double COSTO_POR_KM = 20000.00;
    private final Double SOBRECOSTO_PROVINCIA_DISTINTA = 50000.00;
	
	public double getCostoBaseConexion() {
	    double costo = this.km * COSTO_POR_KM;
	    
	    if (!origen.getProvincia().equals(destino.getProvincia())) {
	        costo += SOBRECOSTO_PROVINCIA_DISTINTA;
	    }
	    return costo;
	}
	//Se sobreescriben los metodos equals y hashcode para poder hacer comparaciones correctamente.
	@Override
	public boolean equals(Object obj) {
	    if (this == obj) return true;
	    if (obj == null || getClass() != obj.getClass()) return false;
	    Conexion otra = (Conexion) obj;
	    return (origen.equals(otra.origen) && destino.equals(otra.destino)) ||
	           (origen.equals(otra.destino) && destino.equals(otra.origen));
	}

	@Override
	public int hashCode() {
	    return origen.hashCode() + destino.hashCode();
	}
	
	@Override
    public String toString() {
        return origen.getNombre() + " - " + destino.getNombre();
    }
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
    
    public Double getKm() {
		return km;
	}

	public void setKm(Double km) {
		this.km = km;
	}

	public Coordinate getCoordenadasOrigen() {
    	return origen.getCordenadasLocalidad();
    }
    
    public Coordinate getCoordenadasDestino() {
    	return destino.getCordenadasLocalidad();
    }
	public Double getCOSTO_POR_KM() {
		return COSTO_POR_KM;
	}
	public Double getSOBRECOSTO_PROVINCIA_DISTINTA() {
		return SOBRECOSTO_PROVINCIA_DISTINTA;
	}
	

}
