package grafo;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openstreetmap.gui.jmapviewer.Coordinate;

import entidades.Conexion;
import entidades.Localidad;
import logica.AlgoritmoAGM;


public class Grafo {

    private Map<Localidad, List<Conexion>> adyacencias;

    public Grafo(Map<Localidad, List<Conexion>> adyacencias) {
        this.adyacencias = adyacencias;
    }
    
    public Grafo() {
        this.adyacencias = new HashMap<>();
    }

    public Map<Localidad, List<Conexion>> getAdyacencias() {
        return adyacencias;
    }
    
    

    public void setAdyacencias(Map<Localidad, List<Conexion>> adyacencias) {
        this.adyacencias = adyacencias;
    }

    
    //Devuelve todas las localidades del grafo
     
    public List<Localidad> obtenerLocalidades() {
        return adyacencias.keySet().stream().toList();
    }

    
    //Devuelve conexiones de una localidad
    
    public List<Conexion> obtenerConexiones(Localidad l) {
        return adyacencias.get(l);
    }

	public void agregarConexion(int nodo1, int nodo2, double peso) {
		// TODO Auto-generated method stub
		
	}

	public int obtenerPeso(int nodo1, int nodo2) {
		int pesoConexion = 0;
		return pesoConexion;
	}

	public void eliminarConexion(int nodo1, int nodo2) {
		// TODO Auto-generated method stub
		
	}

	public void eliminarVariasAristasConexiones(boolean[][] marcados) {
		// TODO Auto-generated method stub
		
	}

	public void eliminarAristaConexionMayorVecinos() {
		// TODO Auto-generated method stub
		
	}

	public void arbolMinimoPrim() {
		// TODO Auto-generated method stub
		
	}

	public Array resultadoMatriz() {
		ArrayList <adyacencias> resultadoMatriz = new ArrayList <adyacencias>();
		return null;
	}

	public Array resultadoArbolMinimo() {
		ArrayList <AlgoritmoAGM> arbolMinimo = new ArrayList <AlgoritmoAGM>();
		return null;
	}
}