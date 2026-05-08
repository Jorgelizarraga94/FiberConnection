package principal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.*;
import org.openstreetmap.gui.jmapviewer.JMapViewer;

import entidades.ControladoraLogica;
import entidades.Localidad;
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
		logicaMapa.dibujar(mapa);
		InterfazDatos interfazDatos = new InterfazDatos(mapa, fiberConnection, logicaMapa);
		interfazDatos.setVisible(true);
	}
}


