package logica;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import entidades.Conexion;
import entidades.Localidad;
import grafo.Grafo;

public class AlgoritmoAGM {

    public Grafo generarAGM(Grafo grafoCompleto) {
        // 1. El grafo resultante que contendrá la planificación óptima de fibra
        Grafo arbolMinimo = new Grafo();

        if (grafoCompleto.getAdyacencias().isEmpty()) {
            return arbolMinimo;
        }

        // --- PASO 1: Inicializar todos los nodos ---
        // Aseguramos que todas las ciudades existan en el nuevo grafo desde el inicio.
        // Esto evita que Rosario o Córdoba desaparezcan del mapa.
        for (Localidad loc : grafoCompleto.getAdyacencias().keySet()) {
            arbolMinimo.getAdyacencias().put(loc, new ArrayList<>());
        }

        // 2. Estructuras para el algoritmo de Prim
        Set<Localidad> visitados = new HashSet<>();
        List<Localidad> todasLasLocalidades = grafoCompleto.obtenerLocalidades();
        
        // Empezamos por la primera localidad de la lista
        Localidad inicial = todasLasLocalidades.get(0);
        visitados.add(inicial);

        // 3. Bucle principal: conectar nodos hasta que todos estén en la red
        while (visitados.size() < todasLasLocalidades.size()) {
            Conexion conexionMasCorta = null;
            double distanciaMinima = Double.MAX_VALUE;

            // Buscamos entre los nodos ya conectados (visitados)
            for (Localidad visitada : visitados) {
                List<Conexion> conexionesPosibles = grafoCompleto.obtenerConexiones(visitada);
                
                if (conexionesPosibles != null) {
                    for (Conexion con : conexionesPosibles) {
                        // Identificamos cuál es el nodo del otro extremo de la arista
                        Localidad destinoReal = (con.getOrigen().equals(visitada)) 
                                                ? con.getDestino() 
                                                : con.getOrigen();

                        // Si ese nodo aún no está en nuestra red, evaluamos su distancia (KM)
                        if (!visitados.contains(destinoReal)) {
                            if (con.getKm() < distanciaMinima) {
                                distanciaMinima = con.getKm();
                                conexionMasCorta = con;
                            }
                        }
                    }
                }
            }

            // 4. Si encontramos la conexión óptima, la agregamos al plan de obra
            if (conexionMasCorta != null) {
                // Identificamos cuál de los dos es el nodo nuevo para agregarlo a visitados
                Localidad nuevoNodo = visitados.contains(conexionMasCorta.getOrigen()) 
                                      ? conexionMasCorta.getDestino() 
                                      : conexionMasCorta.getOrigen();

                // Agregamos la arista al árbol (que ya tiene los nodos cargados en el Paso 1)
                arbolMinimo.agregarConexion(
                    conexionMasCorta.getOrigen(), 
                    conexionMasCorta.getDestino(), 
                    conexionMasCorta.getKm()
                );
                
                visitados.add(nuevoNodo);
            } else {
                // Si llegamos acá y no visitamos todos, es porque el grafo original está cortado
                // (no hay aristas que conecten los nodos restantes con los visitados)
                break;
            }
        }

        return arbolMinimo;
    }
    
}