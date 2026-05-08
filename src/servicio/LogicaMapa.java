package servicio;

import org.openstreetmap.gui.jmapviewer.*;

import entidades.Conexion;
import entidades.ControladoraLogica;
import entidades.Localidad;

import java.awt.Color;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class LogicaMapa {
	public void dibujarGrafo(List<Localidad> localidades, List<Conexion> conexiones, JMapViewer mapa) {
	    
	    // 1. Dibujar las Aristas (Conexiones) primero para que queden debajo de los puntos
		if(conexiones != null) {
			for (Conexion con : conexiones) {
		        Coordinate origen = con.getCoordenadasOrigen();
		        Coordinate destino = con.getCoordenadasDestino();
		        
		        // Creamos la línea usando un MapPolygonImpl de 2 puntos (se repite el destino para cerrar la lógica del polígono)
		        List<Coordinate> puntos = new ArrayList<>(Arrays.asList(origen, destino, destino));
		        MapPolygonImpl linea = new MapPolygonImpl(puntos);
		        
		        linea.setColor(Color.BLUE); // Color de la línea
		        mapa.addMapPolygon(linea);
		    }
		}
	    

		// Dibujar Nodos
	    for (Localidad loc : localidades) {
	        // Usamos Coordinate de JMapViewer
	        Coordinate coord = new Coordinate(loc.getLatitud(), loc.getLongitud());
	        MapMarkerDot marcador = new MapMarkerDot(loc.getNombre(), coord);
	        mapa.addMapMarker(marcador);
	    }
	    
	    // El mapa necesita un repaint aquí también
	    mapa.repaint();
	}
	public void dibujar(JMapViewer mapa) {
		ControladoraLogica controladoraLogica = new ControladoraLogica();
		List<String> listaDatosLocalidades = controladoraLogica.getLocalidadesOrdenadas();
		List<Localidad> listaDeLocalidadesList = new ArrayList<>();
		for (String linea : listaDatosLocalidades) {
		    String[] partes = linea.split(",");
		    
		    Localidad loc = new Localidad(
		        Double.parseDouble(partes[0]), 
		        Double.parseDouble(partes[1]), 
		        partes[2], 
		        partes[3]
		    );
		    listaDeLocalidadesList.add(loc);
		}
		dibujarGrafo(listaDeLocalidadesList, null, mapa);
	}
	
}
