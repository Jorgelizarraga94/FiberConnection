package servicio;
import grafo.Grafo;
import gui.InterfazDatos;
import entidades.Conexion;
import entidades.ControladoraLogica;
import entidades.Localidad;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import javax.swing.DefaultComboBoxModel;
import org.openstreetmap.gui.jmapviewer.Coordinate;

public class FiberConnection {
	
    private Grafo grafo;
	private static Grafo grafoTest;
	
	private ControladoraLogica controlLogica = new ControladoraLogica();
	
    public FiberConnection() {
        this.grafo = new Grafo();
        this.grafoTest = new Grafo(); 
    }
    public Grafo getGrafo() {
        return grafo;
    } 
    
    public void construirGrafo(Localidad localidad) {
        if (!grafo.getAdyacencias().containsKey(localidad)) {
            grafo.getAdyacencias().put(localidad, new ArrayList<Conexion>());
        }
        for (Localidad existente : grafo.getAdyacencias().keySet()) {
            if (!existente.equals(localidad)) {
                double distancia = localidad.distanciaEntreDosPuntos(existente);
                grafo.agregarConexion(localidad, existente, distancia);
            }
        }
    }
    
    public void eliminarLocalidadGrafo(int indice) {
        List<Localidad> localidades = new ArrayList<>(grafo.getAdyacencias().keySet());
        if (indice >= 0 && indice < localidades.size()) {
            Localidad localidadAEliminar = localidades.get(indice);
            grafo.getAdyacencias().remove(localidadAEliminar);
            
    	}
    }

    public List<Conexion> obtenerTodasLasConexiones() {

        List<Conexion> todas = new ArrayList<>();

        for (List<Conexion> lista : grafo.getAdyacencias().values()) {
            for (Conexion conexion : lista) {
                if (!todas.contains(conexion)) {
                    todas.add(conexion);
                }
            }
        }
        return todas;
    }
   
    public Set<Conexion> obtenerAristasUnicas(Grafo grafo) {
        Set<Conexion> aristasUnicas = new HashSet<>();
        for (List<Conexion> lista : grafo.getAdyacencias().values()) {
            aristasUnicas.addAll(lista);
        }
        return aristasUnicas; 
    }
    
    public Double calcularKmTotales(Grafo agm) {
        Double totalKm = 0.0;
        Set<Conexion> aristasSinRepetir = obtenerAristasUnicas(agm);
        for (Conexion con : aristasSinRepetir) {
            totalKm += con.getKm();
        }
        return totalKm;
    }
    
    public Double calcularPresupuesto(Grafo agm) {
        double kmTotales = calcularKmTotales(agm);
        double costoBaseTotal = 0;
        for (Conexion con : obtenerAristasUnicas(agm)) {
            costoBaseTotal += con.getCostoBaseConexion();
        }
        if (kmTotales > 300) {
            costoBaseTotal += (costoBaseTotal * 0.10); 
        }
        return costoBaseTotal;
    }
    
    

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/// 
/// 
///     OBJETIVO TESTING
/// 
/// 
/// 
/// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static int tamanio(Grafo g) {
        return g.obtenerLocalidades().size();
    }

    public static boolean verificarAristaConexion(Grafo g, Localidad l1, Localidad l2) {
        return g.obtenerConexiones(l1).stream()
                .anyMatch(c -> c.getOrigen().equals(l2) || c.getDestino().equals(l2));
    }

    public static Set<Localidad> verificarVecinos(Grafo g, Localidad l) {
        Set<Localidad> vecinos = new HashSet<>();
        for (Conexion c : g.obtenerConexiones(l)) {
            vecinos.add(c.getOrigen().equals(l) ? c.getDestino() : c.getOrigen());
        }
        return vecinos.isEmpty() ? null : vecinos;
    }

    public static boolean estaConectado(List<Localidad> nodos, Localidad l1, Localidad l2) {
        if (nodos == null || !nodos.contains(l1) || !nodos.contains(l2)) return false;
        
        Queue<Localidad> q = new LinkedList<>();
        Set<Localidad> visitados = new HashSet<>();
        q.add(l1);
        visitados.add(l1);
        
        while (!q.isEmpty()) {
            Localidad actual = q.poll();
            if (actual.equals(l2)) return true;
            for (Conexion c : grafoTest.obtenerConexiones(actual)) {
                Localidad v = c.getOrigen().equals(actual) ? c.getDestino() : c.getOrigen();
                if (!visitados.contains(v)) {
                    visitados.add(v);
                    q.add(v);
                }
            }
        }
        return false;
    }

    public static List<Localidad> construirGrafoTest(List<Localidad> localidades) {
        grafoTest = new Grafo();
        for (Localidad l : localidades) {
            if (l != null) grafoTest.getAdyacencias().putIfAbsent(l, new ArrayList<>());
        }
        for (int i = 0; i < localidades.size(); i++) {
            for (int j = i + 1; j < localidades.size(); j++) {
                if (localidades.get(i) != null && localidades.get(j) != null)
                    grafoTest.agregarConexion(localidades.get(i), localidades.get(j), 20.0);
            }
        }
        return grafoTest.obtenerLocalidades();
    }
}
