package fiberConnectionTest;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.openstreetmap.gui.jmapviewer.Coordinate;

import entidades.Conexion;
import entidades.Localidad;
import grafo.Grafo;
import servicio.FiberConnection;

public class AGMTest {
	
	List<Localidad> localidadesTest = new ArrayList<>();
	List<Localidad> localidadesTest2 = new ArrayList<>();
	List<Localidad> localidadesTest3 = new ArrayList<>();
	List<Conexion> conexionLocalidades = new ArrayList<>();
	Localidad sanMiguel = new Localidad(-34.543159,  -58.711774, "San Miguel", "Buenos Aires");
	Localidad bellaVista = new Localidad(-34.5333, -58.6667,"Bella Vista", "Buenos Aires");
	Localidad joseCPaz = new Localidad(-34.51541, -58.76813, "Jose C Paz", "Buenos Aires");
	Localidad cordobaCapital = new Localidad(-31.416666666667, -64.183333333333,"Cordoba", "Cordoba");
	Localidad bariloche = new Localidad(-41.14557, -71.30822,"Bariloche", "Rio Negro");
	Localidad ushuaia  = new Localidad(-54.81084, -68.31591, "Ushuaia ", "Tierra del Fuego");

	@Test
	void crearGrafoTest() {
		localidadesTest.add(sanMiguel);
		localidadesTest.add(bellaVista);
		localidadesTest.add(joseCPaz);
		localidadesTest.add(cordobaCapital);
		localidadesTest.add(bariloche);
		localidadesTest.add(ushuaia);
		var grafoListado = FiberConnection.construirGrafoTest(localidadesTest);
		assertEquals(localidadesTest.size(), grafoListado.size());
	}
	
	@Test
	void crearGrafoTestNull() {
		localidadesTest3.add(null);
		localidadesTest3.add(null);
		localidadesTest3.add(null);
		localidadesTest3.add(null);
		var grafoListado = FiberConnection.construirGrafoTest(localidadesTest3);
		assertNotEquals(localidadesTest3.size(), grafoListado.size());
	}
	
	@Test
	void crearGrafoTestVacio() {
		var grafoListado = FiberConnection.construirGrafoTest(localidadesTest2);
		assertNotEquals(localidadesTest2.size(), grafoListado.size());
	}
	
	
	@Test
	void conectadoTest() {
		conexionLocalidades.add(new Conexion(bariloche, bariloche,50.1));
		conexionLocalidades.add(new Conexion(ushuaia,ushuaia,30.0));
		conexionLocalidades.add(new Conexion(ushuaia,bariloche,30.0));
		
		var grafoConexionesListado = FiberConnection.construirGrafoTest(localidadesTest, conexionLocalidades);
		
		assertTrue(FiberConnection.estaConectado(grafoConexionesListado, ushuaia, bariloche));
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