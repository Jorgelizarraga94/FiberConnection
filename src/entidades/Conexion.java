package entidades;

public class Conexion {
		
	private Localidad origen;
	private Localidad destino;
	
	
	public Conexion(Localidad origen, Localidad destino) {
		this.origen = origen;
		this.destino = destino;
	}
	
	public Localidad getOrigen() {
        return origen;
    }

    public Localidad getDestino() {
        return destino;
    }
    
    @Override
    public String toString() {
        return origen.getNombre() + " - " + destino.getNombre();
    }

}
