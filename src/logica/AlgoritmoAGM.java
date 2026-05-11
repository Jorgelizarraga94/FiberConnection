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
        Grafo arbolMinimo = new Grafo();
        if (grafoCompleto.getAdyacencias().isEmpty()) {
            return arbolMinimo;
        }
        for (Localidad loc : grafoCompleto.getAdyacencias().keySet()) {
            arbolMinimo.getAdyacencias().put(loc, new ArrayList<>());
        }
        Set<Localidad> visitados = new HashSet<>();
        List<Localidad> todasLasLocalidades = grafoCompleto.obtenerLocalidades();
        Localidad inicial = todasLasLocalidades.get(0);
        visitados.add(inicial);
        while (visitados.size() < todasLasLocalidades.size()) {
            Conexion conexionMasCorta = null;
            double distanciaMinima = Double.MAX_VALUE;
            for (Localidad visitada : visitados) {
                List<Conexion> conexionesPosibles = grafoCompleto.obtenerConexiones(visitada);
                if (conexionesPosibles != null) {
                    for (Conexion con : conexionesPosibles) {
                        Localidad destinoReal = (con.getOrigen().equals(visitada)) ? con.getDestino() : con.getOrigen();
                        if (!visitados.contains(destinoReal)) {
                            if (con.getKm() < distanciaMinima) {
                                distanciaMinima = con.getKm();
                                conexionMasCorta = con;
                            }
                        }
                    }
                }
            }
            if (conexionMasCorta != null) {
                Localidad nuevoNodo = visitados.contains(conexionMasCorta.getOrigen())  ? conexionMasCorta.getDestino()  : conexionMasCorta.getOrigen();
                arbolMinimo.agregarConexion(
                    conexionMasCorta.getOrigen(), 
                    conexionMasCorta.getDestino(), 
                    conexionMasCorta.getKm()
                );
                visitados.add(nuevoNodo);
            } else {
                break;
            }
        }
        return arbolMinimo;
    }
    
}