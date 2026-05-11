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

    public int obtenerPeso(Localidad l1, Localidad l2) {
        return obtenerConexiones(l1).stream().filter(c -> c.getOrigen().equals(l2) || c.getDestino().equals(l2)).map(c -> c.getKm().intValue()).findFirst().orElse(0);
    }

    public void eliminarConexion(Localidad l1, Localidad l2) {
        if (adyacencias.containsKey(l1)) 
            adyacencias.get(l1).removeIf(c -> c.getOrigen().equals(l2) || c.getDestino().equals(l2));
        if (adyacencias.containsKey(l2)) 
            adyacencias.get(l2).removeIf(c -> c.getOrigen().equals(l1) || c.getDestino().equals(l1));
    }

    public void arbolMinimoPrim() {
        AlgoritmoAGM alg = new AlgoritmoAGM();
        Grafo agm = alg.generarAGM(this);
        this.adyacencias = agm.getAdyacencias();
    }

    public String resultadoMatriz() {
        StringBuilder sb = new StringBuilder();
        List<Localidad> locs = obtenerLocalidades();
        for (int i = 0; i < locs.size(); i++) {
            Localidad origen = locs.get(i);
            for (Conexion c : obtenerConexiones(origen)) {
                Localidad destino = c.getOrigen().equals(origen) ? c.getDestino() : c.getOrigen();
                sb.append("Punto: ").append(i + 1).append(" al Punto: ").append(locs.indexOf(destino) + 1).append(" Distancia de: ").append(c.getKm()).append("\n");
            }
        }
        return sb.toString();
    }
}