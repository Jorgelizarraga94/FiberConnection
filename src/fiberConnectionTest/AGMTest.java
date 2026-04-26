package fiberConnectionTest;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import org.junit.Test;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import grafo.Grafo;
import servicio.FiberConnection;

public class AGMTest {
	
	ArrayList <Coordinate> coordenadas = new ArrayList <Coordinate>();

	@Test
	void crearGrafoTestTrue() {
		coordenadas.add(new Coordinate(0,0));
		coordenadas.add(new Coordinate(100,100));
		coordenadas.add(new Coordinate(200,200));
		coordenadas.add(new Coordinate(300,300));
		coordenadas.add(new Coordinate(50,50));
		// es necesario cambiar el constructor de este test para que en vez de recibir una cordenada reciba una localidad y de esa forma
		// el propio constructor del grafo y fiberConnection puedan utilizar el mapa ya asignado 
		Grafo grafo = new Grafo(coordenadas.size(), coordenadas);
		
		assertTrue(FiberConnection.construirGrafo(grafo));
	}
	
	@Test
	void crearGrafoTestFalseNull() {
		Grafo grafo = new Grafo(0,null);
		
		assertFalse(FiberConnection.construirGrafo(grafo));
	}
	
	@Test
	void crearGrafoTestFalseVacio() {
		ArrayList <Coordinate> coordenadasVacias = new ArrayList <Coordinate>();
		Grafo grafoTest = new Grafo(0,coordenadasVacias);
		
		assertFalse(FiberConnection.construirGrafo(grafoTest));
	}
	
	
	@Test
	void conectadoTest() {
		coordenadas.add(new Coordinate(0,0));
		coordenadas.add(new Coordinate(100,100));
		coordenadas.add(new Coordinate(200,200));
		Grafo grafo = new Grafo(coordenadas.size(), coordenadas);
		FiberConnection.construirGrafo(grafo);
		
		assertTrue(FiberConnection.estaConectado(new Coordinate(0,0), new Coordinate(100,100)));
	}
	@Test
	void distanciaEuclideaTest() {

	}
	
	@Test
	void distanciaEuclideaTestError() {

	}
	
	@Test
	void resultadoArbolMinimoTest(){

	}
}