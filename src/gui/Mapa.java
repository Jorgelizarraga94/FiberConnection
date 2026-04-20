package gui;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;
import entidades.Localidad;
import entidades.LocalidadManager;

import javax.swing.JFrame;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;
import org.openstreetmap.gui.jmapviewer.interfaces.MapPolygon;

import java.awt.BorderLayout;

public class Mapa {

	private JFrame frame;
	
	private JMapViewer mapa;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Mapa window = new Mapa();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Mapa() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setTitle("Mapa");
		
		
		/*
		//Creo el mapa
		mapa = new JMapViewer();
		//Quitar la barra de zoom
		mapa.setZoomContolsVisible(false);
		
		
		//Coordenada para practicar
		Coordinate coordenada = new Coordinate(-34.5111, -58.8183); 
		mapa.setDisplayPosition(coordenada, 12); 
		
		//Agregar un marcador 
		MapMarker marker = new MapMarkerDot("Aca vive Martin", coordenada);
		marker.getStyle().setBackColor(java.awt.Color.RED); // Cambia el color del marcador a rojo
		marker.getStyle().setColor(java.awt.Color.BLACK); // Cambia el borde del marcador a
		mapa.addMapMarker(marker);
		
		
		//Otra marca azul en el centro de san miguel
		MapMarker marker2 = new MapMarkerDot("Aca vive Jorge", new Coordinate(-34.54498139424723, -58.69392011418977 ));
		marker2.getStyle().setBackColor(java.awt.Color.BLUE); // Cambia el color del marcador a azul
		marker2.getStyle().setColor(java.awt.Color.BLACK); // Cambia el borde del marcador a negro
		mapa.addMapMarker(marker2);
		
		//Marcador de la ungs 
		MapMarker marker3 = new MapMarkerDot("UNGS", new Coordinate(-34.5222, -58.7000 ));
		marker3.getStyle().setBackColor(java.awt.Color.GREEN); // Cambia el color del marcador a verde
		marker3.getStyle().setColor(java.awt.Color.BLACK); // Cambia el borde del marcador
		mapa.addMapMarker(marker3);
		
		
		
		
		//array para hacer un poligono para hacer lineas entre los puntos
		ArrayList<Coordinate> coordenadas = new ArrayList<Coordinate>(); 
		
		
		
		MapPolygon poligono = new MapPolygonImpl(coordenadas);
		//Poligono de las 3 marcas anteriores
		coordenadas.add(coordenada);
		coordenadas.add(new Coordinate(-34.54498139424723, -58.69392011418977 ));
		coordenadas.add(new Coordinate(-34.5222, -58.7000 ));
		
		
		
		
		mapa.addMapPolygon(poligono);
		
		
		frame.getContentPane().add(mapa, BorderLayout.CENTER); 
		*/
		
		
		//Creo el mapa
		mapa = new JMapViewer();
		//Quitar la barra de zoom
		mapa.setZoomContolsVisible(false);
		
		//Iniciar el mapa en la coordenada de san miguel
		Coordinate coordenada = new Coordinate(-34.54498139424723, -58.69392011418977 );
		// Si hay localidades guardadas, centrar en la primera
		
		mapa.setDisplayPosition(coordenada, 12);
		frame.getContentPane().add(mapa, BorderLayout.CENTER);
		
		
		
		
		
	}

	
	//Hacer visible el mapa
	public void setVisible(boolean b) {
		frame.setVisible(b);
		return ;
		
	}

}
