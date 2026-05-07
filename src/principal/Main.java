package principal;

import javax.swing.*;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import gui.InterfazDatos;
import servicio.FiberConnection;
import servicio.LogicaMapa;

	public class Main extends JFrame
	{
		public static void main(String[] args) 
		{
			JMapViewer mapa = new JMapViewer();
			FiberConnection fiberConnection = new FiberConnection();
			LogicaMapa logicaMapa = new LogicaMapa();
			InterfazDatos interfazDatos = new InterfazDatos(mapa, fiberConnection, logicaMapa);
			interfazDatos.setVisible(true);
		}
	}


