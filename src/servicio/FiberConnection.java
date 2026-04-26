package servicio;

import grafo.Grafo;
import entidades.Conexion;
import entidades.Localidad;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openstreetmap.gui.jmapviewer.Coordinate;

public class FiberConnection {

    private Grafo grafo;

    public FiberConnection() {
        this.grafo = new Grafo();
    }

    public Grafo getGrafo() {
        return grafo;
    }

    
    public void construirGrafo(List<Localidad> localidades) {

        Map<Localidad, List<Conexion>> adyacencias = new HashMap<>();

        //Inicializar nodos
        for (Localidad loc : localidades) {
            adyacencias.put(loc, new ArrayList<>());
        }

        //Crear grafo completo 
        for (int i = 0; i < localidades.size(); i++) {
            for (int j = i + 1; j < localidades.size(); j++) {

                Localidad origen = localidades.get(i);
                Localidad destino = localidades.get(j);

                Conexion conexion = new Conexion(origen, destino);

                //agregar en ambos sentidos (grafo no dirigido)
                adyacencias.get(origen).add(conexion);
                adyacencias.get(destino).add(conexion);
            }
        }

       
        grafo.setAdyacencias(adyacencias);
    }

    
    //Devuelve todas las conexiones del grafo en una sola lista
    //(util para AGM despues).
    
    public List<Conexion> obtenerTodasLasConexiones() {

        List<Conexion> todas = new ArrayList<>();

        for (List<Conexion> lista : grafo.getAdyacencias().values()) {
            for (Conexion c : lista) {
                if (!todas.contains(c)) {
                    todas.add(c);
                }
            }
        }

        return todas;
    }

   
    //Devuelve todas las localidades cargadas.
    
    public List<Localidad> obtenerLocalidades() {
        return new ArrayList<>(grafo.getAdyacencias().keySet());
    }

	public static boolean estaConectado(Coordinate coordenada1, Coordinate coordenada2) {
		boolean conectado = false;
		return conectado;
	}

	public static int tamanio(Grafo grafo) {
		int tamanio = 0;
		return tamanio;
	}

	public static boolean verificarDistintosTamanios(Grafo grafo) {
		boolean tamanioCorrecto = false;
		return tamanioCorrecto;
	}

	public static boolean verificarNodoLocalidad(Grafo grafo) {
		boolean nodoCorrecto = false;
		return nodoCorrecto;
	}

	public static boolean verificarAristaConexion(Grafo grafo, int nodo1, int nodo2) {
		boolean conexionCorrecta = false;
		return conexionCorrecta;
	}
}