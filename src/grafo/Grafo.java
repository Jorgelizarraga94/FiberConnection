package grafo;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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

	public void agregarConexion(Localidad localidadA, Localidad localidadB, Double km) {
		// Las conexiones en estos grafos suelen ser bidireccionales
	    Conexion nuevaConexion = new Conexion(localidadA, localidadB, km);
	    
	 // 2. SEGURIDAD: Si la localidadA no existe en el mapa, la creamos con una lista vacía
	    if (!adyacencias.containsKey(localidadA)) {
	        adyacencias.put(localidadA, new ArrayList<>());
	    }

	    // 3. SEGURIDAD: Lo mismo para la localidadB
	    if (!adyacencias.containsKey(localidadB)) {
	        adyacencias.put(localidadB, new ArrayList<>());
	    }
	    
	    // Agregamos la conexión a la lista de adyacencia de AMBAS localidades
	    if(adyacencias != null && localidadA != null && localidadB != null) {
	    	
	    	adyacencias.get(localidadA).add(nuevaConexion);
	    	adyacencias.get(localidadB).add(nuevaConexion);
	    }
		
	}

	public int obtenerPeso(Localidad localidad1, Localidad localidad2) {
		int pesoConexion = 0;
		return pesoConexion;
	}

	public void eliminarConexion(Localidad localidad1, Localidad localidad2) {
		// TODO Auto-generated method stub
		
	}

	public void eliminarVariasAristasConexiones(Map<Localidad, Map<Localidad, Boolean>> marcados) {
		// TODO Auto-generated method stub
		
	}

	public void eliminarAristaConexionMayorVecinos() {
		// TODO Auto-generated method stub
		
	}

	public void arbolMinimoPrim() {
		// TODO Auto-generated method stub
		
	}

	public Array resultadoMatriz() {
//		ArrayList <> resultadoMatriz = new ArrayList <>();
		return null;
	}

	public Array resultadoArbolMinimo() {
		ArrayList <AlgoritmoAGM> arbolMinimo = new ArrayList <AlgoritmoAGM>();
		return null;
	}
}