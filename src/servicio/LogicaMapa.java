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
	    mapa.removeAllMapMarkers(); 
	    mapa.removeAllMapPolygons();
	    for (Localidad loc : grafo.getAdyacencias().keySet()) {
	        Coordinate coord = new Coordinate(loc.getLatitud(), loc.getLongitud());
	        MapMarkerDot marcador = new MapMarkerDot(loc.getNombre(), coord);
	        marcador.getStyle().setBackColor(Color.red);
	        mapa.addMapMarker(marcador);
	        
	        
	        for (Conexion con : grafo.obtenerConexiones(loc)) {
	            Localidad destino = con.getDestino();
	            Coordinate coordDestino = new Coordinate(destino.getLatitud(), destino.getLongitud());
	            List<Coordinate> puntos = Arrays.asList(coord, coordDestino, coordDestino);
	            MapPolygonImpl linea = new MapPolygonImpl(puntos);
	            mapa.addMapPolygon(linea);
	            
	            double kmTotales = con.getKm();
		        double factorRecargo = (kmTotales > 300) ? 1.10 : 1.0;
	            
	            if (loc.getNombre().compareTo(destino.getNombre()) < 0) {
	                
	                double latMedia = (loc.getLatitud() + destino.getLatitud()) / 2;
	                double lonMedia = (loc.getLongitud() + destino.getLongitud()) / 2; 
	                Coordinate puntoMedio = new Coordinate(latMedia, lonMedia);

	                double costoIndividualCompleto = con.getCostoBaseConexion() * factorRecargo;
	                costoIndividualCompleto /= kmTotales;
	                String textoCosto = String.format("$%,.2f x km", costoIndividualCompleto);
	                MapMarkerDot etiquetaKm = new MapMarkerDot(textoCosto, puntoMedio);
	                
	                etiquetaKm.getStyle().setBackColor(new Color(0, 0, 0, 0));
	                etiquetaKm.getStyle().setColor(null);
	                mapa.addMapMarker(etiquetaKm);
	            } 
	        }
	    }
	}
	
	public void actualizarMapa(Grafo grafoActual, JMapViewer mapa) {
	    mapa.removeAllMapMarkers();
	    mapa.removeAllMapPolygons();
	    dibujar(mapa, grafoActual);
	    mapa.repaint();
	    mapa.revalidate();
	}
}
