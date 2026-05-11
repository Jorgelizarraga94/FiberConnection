package grafo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import entidades.Conexion;
import entidades.Localidad;
import logica.AlgoritmoAGM;

public class Grafo {
    private Map<Localidad, List<Conexion>> adyacencias;

    public Grafo() {
        this.adyacencias = new HashMap<>();
    }

    public Map<Localidad, List<Conexion>> getAdyacencias() {
        return adyacencias;
    }

    public void setAdyacencias(Map<Localidad, List<Conexion>> adyacencias) {
        this.adyacencias = adyacencias;
    }

    public List<Localidad> obtenerLocalidades() {
        return new ArrayList<>(adyacencias.keySet());
    }
    
    public List<Conexion> obtenerConexiones(Localidad l) {
        return adyacencias.getOrDefault(l, new ArrayList<>());
    }

    public void agregarConexion(Localidad localidadA, Localidad localidadB, Double km) {
        if (localidadA == null || localidadB == null) return;
        Conexion nuevaConexion = new Conexion(localidadA, localidadB, km);
        adyacencias.putIfAbsent(localidadA, new ArrayList<>());
        adyacencias.putIfAbsent(localidadB, new ArrayList<>());
        boolean existe = adyacencias.get(localidadA).stream().anyMatch(c -> c.getOrigen().equals(localidadB) || c.getDestino().equals(localidadB));
        if (!existe) {
            adyacencias.get(localidadA).add(nuevaConexion);
            adyacencias.get(localidadB).add(nuevaConexion);
        }
    }

    public int obtenerPeso(Localidad localidad1, Localidad localidad2) {
        return obtenerConexiones(localidad1).stream().filter(c -> c.getOrigen().equals(localidad2) || c.getDestino().equals(localidad2)).map(c -> c.getKm().intValue()).findFirst().orElse(0);
    }

    public void eliminarConexion(Localidad localidad1, Localidad localidad2) {
        if (adyacencias.containsKey(localidad1)) 
            adyacencias.get(localidad1).removeIf(c -> c.getOrigen().equals(localidad2) || c.getDestino().equals(localidad2));
        if (adyacencias.containsKey(localidad2)) 
            adyacencias.get(localidad2).removeIf(c -> c.getOrigen().equals(localidad1) || c.getDestino().equals(localidad1));
    }

    public void arbolMinimoPrim() {
        AlgoritmoAGM algotirmoAgm = new AlgoritmoAGM();
        Grafo agm = algotirmoAgm.generarAGM(this);
        this.adyacencias = agm.getAdyacencias();
    }
}