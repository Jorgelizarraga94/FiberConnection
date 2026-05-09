package servicio;

import org.openstreetmap.gui.jmapviewer.*;

import entidades.Conexion;
import entidades.ControladoraLogica;
import entidades.Localidad;
import grafo.Grafo;

import java.awt.Color;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class LogicaMapa {
	
	public void dibujar(JMapViewer mapa, Grafo grafo) {
	    // 1. Limpiamos el mapa
	    mapa.removeAllMapMarkers(); 
	    mapa.removeAllMapPolygons();

	    // 2. Dibujamos los Nodos (Localidades)
	    // Recorremos las llaves del HashMap que ya conocemos
	    for (Localidad loc : grafo.getAdyacencias().keySet()) {
	        Coordinate coord = new Coordinate(loc.getLatitud(), loc.getLongitud());
	        MapMarkerDot marcador = new MapMarkerDot(loc.getNombre(), coord);
	        mapa.addMapMarker(marcador);
	        
	        // 3. Dibujamos las Aristas (Conexiones) para cada localidad
	        for (Conexion con : grafo.obtenerConexiones(loc)) {
	            Localidad destino = con.getDestino();
	            Coordinate coordDestino = new Coordinate(destino.getLatitud(), destino.getLongitud());
	            
	            // Creamos la línea visual entre los dos puntos
	            List<Coordinate> puntos = Arrays.asList(coord, coordDestino, coordDestino);
	            MapPolygonImpl linea = new MapPolygonImpl(puntos);
	            mapa.addMapPolygon(linea);
	        }
	    }
	}
	
}
