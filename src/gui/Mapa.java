package gui;

import java.util.List;
import entidades.Localidad;
import javax.swing.JFrame;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import java.awt.BorderLayout;

public class Mapa {

	private JFrame frame;
	
	private JMapViewer mapa;

	public Mapa() {
		initialize();
	}

	private List<Localidad> localidades;

    public Mapa(List<Localidad> localidades) {
        this.localidades = localidades;

        initialize();
    }

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setTitle("Mapa");
		
		
		
		//Creo el mapa
		mapa = new JMapViewer();
		//Quitar la barra de zoom
		mapa.setZoomContolsVisible(false);
		frame.getContentPane().add(mapa, BorderLayout.CENTER); 
		//Para que quede en jcp
		Coordinate coordenada = new Coordinate(-34.5111, -58.8183); 
		mapa.setDisplayPosition(coordenada, 12);
		
	}

	
	//Hacer visible el mapa
	public void setVisible(boolean b) {
		frame.setVisible(b);
		return ;
		
	}

}
