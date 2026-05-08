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
		    System.out.println("Procesando: " + loc.getNombre());
		    
		    
		}
		logicaMapa.dibujarGrafo(listaDeLocalidadesList, null, mapa);
		InterfazDatos interfazDatos = new InterfazDatos(mapa, fiberConnection, logicaMapa);
		interfazDatos.setVisible(true);
	}
}


