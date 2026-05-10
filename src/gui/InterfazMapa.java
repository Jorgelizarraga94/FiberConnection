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
		 // 1. Definir el layout para que el mapa ocupe todo el espacio
        setLayout(new BorderLayout());

        // 2. Si por alguna razón llega nulo, lo creamos (seguridad)
        if (mapa == null) {
        	mapa = new JMapViewer();
        }

        // 3. AGREGAR el mapa que viene desde el Main al panel
        add(mapa, BorderLayout.CENTER);
        
        // 4. Refrescar
        revalidate();
        repaint();
		
      //Para que quede en la UNGS
      	Coordinate coordenada = new Coordinate(-31.40628337 , -64.19526712); 
      	mapa.setDisplayPosition(coordenada, 4);
	}

	
	public JMapViewer getMapa() {
        return mapa;
    }
	
	
	
	//Hacer visible el mapa
	public void setVisible(boolean b) {
		frame.setVisible(b);
		return ;
		
	}

}
