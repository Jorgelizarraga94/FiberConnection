package servicio;

import grafo.Grafo;
import gui.InterfazDatos;
import entidades.Conexion;
import entidades.Localidad;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.DefaultComboBoxModel;

import org.openstreetmap.gui.jmapviewer.Coordinate;

public class FiberConnection {

    private Grafo grafo;
    private static Grafo grafoTest;
    

    public FiberConnection() {
        this.grafo = new Grafo();
        this.grafoTest = new Grafo();
        
    }

    public Grafo getGrafo() {
        return grafo;
    }

    
    public void construirGrafo(Localidad localidad) {
    	// 1. Primero aseguramos que la nueva localidad esté en el sistema
        if (!grafo.getAdyacencias().containsKey(localidad)) {
            grafo.getAdyacencias().put(localidad, new ArrayList<Conexion>());
        }

        // 2. Conectamos la nueva localidad con TODAS las que ya estaban cargadas
        // Esto crea el "Grafo Completo" necesario para que el AGM pueda elegir
        for (Localidad existente : grafo.getAdyacencias().keySet()) {
            if (!existente.equals(localidad)) {
                double distancia = localidad.distanciaEntreDosPuntos(existente);
                grafo.agregarConexion(localidad, existente, distancia);
            }
        }
    }
    
    public void eliminarLocalidadGrafo(int indice) {
    	// 1. Obtenemos la lista de localidades (nodos) del grafo
        List<Localidad> localidades = new ArrayList<>(grafo.getAdyacencias().keySet());
        // 2. Verificamos que el índice sea válido para evitar errores
        if (indice >= 0 && indice < localidades.size()) {
            Localidad localidadAEliminar = localidades.get(indice);
            // 3. Eliminamos la localidad del mapa de adyacencias
            grafo.getAdyacencias().remove(localidadAEliminar);
    	}
    }

    
    //Devuelve todas las conexiones del grafo en una sola lista
    //(util para AGM despues).
    
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
        // Como el Set usa el equals/hashCode que definimos, 
        // automáticamente elimina los duplicados bidireccionales.
    }
    
    public Double calcularKmTotales(Grafo agm) {
        Double totalKm = 0.0;
        
        // 1. Obtenemos solo una arista por cada par de ciudades conectadas
        Set<Conexion> aristasSinRepetir = obtenerAristasUnicas(agm);
        
        // 2. Sumamos el kilometraje de cada cable de fibra óptica
        for (Conexion con : aristasSinRepetir) {
            totalKm += con.getKm();
        }
        
        // Retornamos el total (podes usar Math.round si queres redondear)
        return totalKm;
    }
    
    public Double calcularPresupuesto(Grafo agm) {
        double kmTotales = calcularKmTotales(agm);
        double costoBaseTotal = 0;
        
        // Sumamos los costos base de las aristas únicas
        for (Conexion con : obtenerAristasUnicas(agm)) {
            costoBaseTotal += con.getCostoBaseConexion();
        }
        
        // Aplicamos el recargo si la obra supera los 300 km
        if (kmTotales > 300) {
            // Suponiendo un 10% de recargo
            costoBaseTotal += (costoBaseTotal * 0.10); 
        }
        return costoBaseTotal;
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

	public static boolean verificarAristaConexion(Grafo grafo, Localidad localidad1, Localidad localidad2) {
		boolean conexionCorrecta = false;
		return conexionCorrecta;
	}

	public static Integer verificarVecinos(Grafo grafo, Localidad localidad) {
		int cantVecinosActual = 0;
		return cantVecinosActual;
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/// 
/// 
/// 
/// 
/// 
/// 
/// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	
    public static List<Localidad> construirGrafoTest(List<Localidad> localidades) {

        Map<Localidad, List<Conexion>> adyacencias = new HashMap<>();

        //Inicializar nodos
        for (Localidad localidad : localidades) {
            adyacencias.put(localidad, new ArrayList<>());
        }

        //Crear grafo completo 
        for (int i = 0; i < localidades.size(); i++) {
            for (int j = i + 1; j < localidades.size(); j++) {

                Localidad origen = localidades.get(i);
                Localidad destino = localidades.get(j);

                Conexion conexion = new Conexion(origen, destino,20.0);

                //agregar en ambos sentidos (grafo no dirigido)
                adyacencias.get(origen).add(conexion);
                adyacencias.get(destino).add(conexion);
            }
        }

       
        grafoTest.setAdyacencias(adyacencias);
        return grafoTest.obtenerLocalidades();
    }
    public static List<Localidad> construirGrafoTest(List<Localidad> localidades, List<Conexion> conexiones) {

        Map<Localidad, List<Conexion>> adyacencias = new HashMap<>();

        //Inicializar nodos
        for (Localidad localidad : localidades) {
            adyacencias.put(localidad, new ArrayList<>());
        }

        //Crear grafo completo 
        for (int i = 0; i < localidades.size(); i++) {
            for (int j = i + 1; j < localidades.size(); j++) {

                Localidad origen = localidades.get(i);
                Localidad destino = localidades.get(j);

                // Todavía no lo hice pero es necesario cambiar la lógica de las conexiones para que reciba las conexiones del parámetro y solo cree esas 
                Conexion conexion = new Conexion(origen, destino,20.0);

                //agregar en ambos sentidos (grafo no dirigido)
                adyacencias.get(origen).add(conexion);
                adyacencias.get(destino).add(conexion);
            }
        }

       
        grafoTest.setAdyacencias(adyacencias);
        return grafoTest.obtenerLocalidades();
    }

	public static boolean estaConectado(List<Localidad> grafoConexionesListado, Localidad localidad1,
			Localidad localidad2) {
		// TODO Auto-generated method stub
		return false;
	}

	}
