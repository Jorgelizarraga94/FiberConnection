package grafo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entidades.Conexion;
import entidades.Localidad;

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
}