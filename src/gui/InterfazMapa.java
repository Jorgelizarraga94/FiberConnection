package gui;

import java.util.List;
import entidades.Localidad;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import java.awt.BorderLayout;

public class InterfazMapa extends JPanel {
	private JFrame frame;
	private JMapViewer mapa;
	public InterfazMapa(JMapViewer mapa) {
		this.mapa = mapa;
		initialize();
	}
	private List<Localidad> localidades;
    public InterfazMapa(List<Localidad> localidades) {
        this.localidades = localidades;
        initialize();
    }
	private void initialize() {
        setLayout(new BorderLayout());
        if (mapa == null) {
        	mapa = new JMapViewer();
        }
        add(mapa, BorderLayout.CENTER);
        revalidate();
        repaint();
      	Coordinate coordenada = new Coordinate(-31.40628337 , -64.19526712); 
      	mapa.setDisplayPosition(coordenada, 4);
	}
	public JMapViewer getMapa() {
        return mapa;
    }
	public void setVisible(boolean b) {
		frame.setVisible(b);
		return ;	
	}
}
