package fiberConnectionTest;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Map;
import org.junit.Test;

import entidades.Localidad;
import grafo.Grafo;
import servicio.FiberConnection;

public class GrafoTest{
	Localidad sanMiguel = new Localidad(-34.543159,  -58.711774, "San Miguel", "Buenos Aires");
	Localidad bellaVista = new Localidad(-34.5333, -58.6667,"Bella Vista", "Buenos Aires");
	Localidad joseCPaz = new Localidad(-34.51541, -58.76813, "Jose C Paz", "Buenos Aires");
	Localidad cordobaCapital = new Localidad(-31.416666666667, -64.183333333333,"Cordoba Capital", "Cordoba");
	Localidad bariloche = new Localidad(-41.14557, -71.30822,"Bariloche", "Rio Negro");
	Localidad ushuaia  = new Localidad(-54.81084, -68.31591, "Ushuaia ", "Tierra del Fuego");
	
	
	Grafo grafo = new Grafo();
	Grafo grafo2 = new Grafo();
	Grafo grafo3 = new Grafo();
	Grafo grafo4 = new Grafo();
	Grafo grafo5 = new Grafo();
@Test
	void grafoTest() {
		
		
		assertEquals(FiberConnection.tamanio(grafo), null);
	}
	
	@Test
	void verificarDistintosTestTrue() {
		
		
		assertTrue(FiberConnection.verificarDistintosTamanios(grafo2));
	}
	
	@Test
	void verificarDistintosTestFalse() {
		
		
		assertFalse(FiberConnection.verificarDistintosTamanios(grafo));
	}
	
	@Test
	void verificarVerticeTestTrue() {
		
		
		assertTrue(FiberConnection.verificarNodoLocalidad(grafo));
	}
	
	@Test
	void verificarVerticeTestFalse() {
		
		
		assertFalse(FiberConnection.verificarNodoLocalidad(grafo));
	}
	
	@Test
	void existeAristaTestTrue() {
		
		grafo3.agregarConexion(sanMiguel, bellaVista, 1.0);
		
		assertTrue(FiberConnection.verificarAristaConexion(grafo3, sanMiguel , bellaVista));
	}
	
	@Test
	void existeAristaTestFalse() {
		
		grafo4.agregarConexion(sanMiguel, bellaVista, (double) 0);
		
		assertFalse(FiberConnection.verificarAristaConexion(grafo4, sanMiguel, joseCPaz));
	}
	
//	@Test
//	void existeAristaTestFalseCondicion() {
//		
//		grafo4.agregarConexion(0, 1, (double) 0);
//		
//		assertFalse(FiberConnection.verificarAristaConexion(grafo4));
//	}
	
	@Test
	void vecinosTestTrue() {
		
		grafo4.agregarConexion(sanMiguel, bellaVista, (double) 0);
		grafo4.agregarConexion(sanMiguel, joseCPaz, (double) 0);
		
		Set<Object> vecinos = new HashSet<Object>();
		vecinos.add(bellaVista);
		vecinos.add(joseCPaz);	
		
		assertEquals(FiberConnection.verificarVecinos(grafo4, sanMiguel), vecinos);
	}
	
	@Test
	void vecinosTestFalse() {
		
		grafo4.agregarConexion(sanMiguel, bellaVista, (double) 0);
		grafo4.agregarConexion(sanMiguel, joseCPaz, (double) 0);
		
		assertEquals(FiberConnection.verificarVecinos(grafo4, cordobaCapital), null);
	}
	
	@Test
	void obtenerPesoTest() {
		
		grafo4.agregarConexion(sanMiguel, bellaVista, 10.0);

		assertEquals(grafo4.obtenerPeso(sanMiguel, bellaVista), 10);
	}
	
	@Test
	void eliminarAristaTest() {
		
		grafo4.agregarConexion(sanMiguel, bellaVista,  0.0);
		grafo4.agregarConexion(sanMiguel, joseCPaz,  0.0);
		
		grafo4.eliminarConexion(sanMiguel, bellaVista);
		
		assertFalse(FiberConnection.verificarAristaConexion(grafo4, sanMiguel, bellaVista));
	}
	
	@Test
	void eliminarVeriasAristasMenosTest() {
		
		grafo5.agregarConexion(sanMiguel, bellaVista, (double) 0);
		grafo5.agregarConexion(sanMiguel, joseCPaz, (double) 0);
		grafo5.agregarConexion(sanMiguel, cordobaCapital, (double) 0);
		Map<Localidad, Map<Localidad, Boolean>> marcados = new HashMap<>();			
		marcar(marcados, sanMiguel, bellaVista);
	    marcar(marcados, bellaVista, sanMiguel);
		
		grafo5.eliminarVariasAristasConexiones(marcados);
		
		assertTrue(FiberConnection.verificarAristaConexion(grafo5, sanMiguel, bellaVista) && !FiberConnection.verificarAristaConexion(grafo5, sanMiguel, cordobaCapital));
	}
	
	@Test
	void eliminarPesoMayorAristaTestTrue() {
		
		grafo4.agregarConexion(sanMiguel, bellaVista, 0.333);
		grafo4.agregarConexion(bellaVista, joseCPaz, 100.0);
		grafo4.agregarConexion(joseCPaz, cordobaCapital, 7.799);
		
		grafo4.eliminarAristaConexionMayorVecinos();
		
		assertFalse(FiberConnection.verificarAristaConexion(grafo4, sanMiguel, bellaVista));
	}
	
	@Test
	void eliminarPesoMayorAristaTestFalse() {
		
		grafo4.agregarConexion(sanMiguel, bellaVista, 10.0);
		
		grafo4.eliminarAristaConexionMayorVecinos();
		
		assertTrue(FiberConnection.verificarAristaConexion(grafo4, sanMiguel, bellaVista));
	}
	
	@Test
	void arbolMinimoPrimTrue() {
		
		grafo4.agregarConexion(sanMiguel, bellaVista, 10.0);
		grafo4.agregarConexion(sanMiguel, joseCPaz, 0.333);
		grafo4.agregarConexion(sanMiguel, cordobaCapital, 8.0);
		grafo4.agregarConexion(bellaVista, joseCPaz, 3.333);
		grafo4.agregarConexion(bellaVista, cordobaCapital, 6.66);
		grafo4.agregarConexion(joseCPaz, cordobaCapital, 0.001);
		
		grafo4.arbolMinimoPrim();
		
		assertFalse(FiberConnection.verificarAristaConexion(grafo4, sanMiguel, bellaVista));
	}
	
	@Test
	void arbolMinimoPrimFalse() {
		
		grafo3.agregarConexion(sanMiguel, bellaVista, 10.0);
		grafo3.agregarConexion(sanMiguel,joseCPaz, 0.333);
		
		grafo3.arbolMinimoPrim();
		
		assertTrue(FiberConnection.verificarAristaConexion(grafo3,sanMiguel, bellaVista));
	}
	
	@Test
	void resultadoMatriz() {
		
		grafo3.agregarConexion(sanMiguel, bellaVista, 10.0);
		
		assertEquals(grafo3.resultadoMatriz(), "Punto: 1 al Punto: 2 Distancia de: 10.0\n"
				+ "Punto: 2 al Punto: 1 Distancia de: 10.0\n");		
	}
	
	@Test
	void resultadoArbolMinimo() {
		
		grafo.agregarConexion(sanMiguel, bellaVista, 10.0);
		
		grafo.arbolMinimoPrim();
		
		assertEquals(grafo.resultadoArbolMinimo(), "");		
	}
	private void marcar(Map<Localidad, Map<Localidad, Boolean>> mapa, Localidad desde, Localidad hasta) {
	    mapa.computeIfAbsent(desde, k -> new HashMap<>()).put(hasta, true);
	}
}