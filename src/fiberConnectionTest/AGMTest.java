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
	Localidad sanMiguel = new Localidad("San Miguel", "Buenos Aires", -34.5333,  -58.7167);
	Localidad bellaVista = new Localidad("Bella Vista", "Buenos Aires", -34.5333, -58.6667);
	Localidad joseCPaz = new Localidad("Jose C Paz", "Buenos Aires",  -34.51541, -58.76813);
	Localidad cordobaCapital = new Localidad("Cordoba Capital", "Cordoba", -31.416666666667, -64.183333333333);
	Localidad bariloche = new Localidad("Bariloche", "Rio Negro", -41.14557, -71.30822);
	Localidad ushuaia  = new Localidad("Ushuaia ", "Tierra del Fuego", -54.81084, -68.31591);

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
		conexionLocalidades.add(new Conexion(bariloche, bariloche));
		conexionLocalidades.add(new Conexion(ushuaia,ushuaia));
		conexionLocalidades.add(new Conexion(ushuaia,bariloche));
		
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