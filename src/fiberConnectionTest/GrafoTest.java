package fiberConnectionTest;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import grafo.Grafo;
import servicio.FiberConnection;

public class GrafoTest{

	Grafo grafo = new Grafo(1);
	Grafo grafo2 = new Grafo(0);
	Grafo grafo3 = new Grafo(2);
	Grafo grafo4 = new Grafo(3);
	Grafo grafo5 = new Grafo(4);
@Test
	void grafoTest() {
		
		
		assertEquals(FiberConnection.tamanio(grafo));
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
		
		grafo3.agregarConexion(0, 1, 1.0);
		
		assertTrue(FiberConnection.verificarAristaConexion(grafo3, 0 , 1));
	}
	
	@Test
	void existeAristaTestFalse() {
		
		grafo4.agregarConexion(0, 1, (double) 0);
		
		assertFalse(FiberConnection.verificarAristaConexion(grafo4, 0, 2));
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
		
		grafo4.agregarConexion(0, 1, (double) 0);
		grafo4.agregarConexion(0, 2, (double) 0);
		
		Set<Integer> vecinos = new HashSet<Integer>();
		vecinos.add(1);
		vecinos.add(2);	
		
		assertEquals(grafo4.vecinos(0), vecinos);
	}
	
	@Test
	void vecinosTestFalse() {
		
		grafo4.agregarConexion(0, 1, (double) 0);
		grafo4.agregarConexion(0, 2, (double) 0);
		
		assertEquals(grafo4.vecinos(3), null);
	}
	
	@Test
	void obtenerPesoTest() {
		
		grafo4.agregarConexion(0, 1, 10.5);

		assertEquals(grafo4.obtenerPeso(0, 1), 10.5);
	}
	
	@Test
	void eliminarAristaTest() {
		
		grafo4.agregarConexion(0, 1, (double) 0);
		grafo4.agregarConexion(0, 2, (double) 0);
		
		grafo4.eliminarConexion(0, 1);
		
		assertFalse(FiberConnection.verificarAristaConexion(grafo4, 0, 1));
	}
	
	@Test
	void eliminarVeriasAristasMenosTest() {
		
		grafo5.agregarConexion(0, 1, (double) 0);
		grafo5.agregarConexion(0, 2, (double) 0);
		grafo5.agregarConexion(0, 3, (double) 0);
		boolean[][] marcados = new boolean[4][4];			
		marcados[0][1] = true;
		marcados[1][0] = true;
		
		grafo5.eliminarVariasAristasConexiones(marcados);
		
		assertTrue(FiberConnection.verificarAristaConexion(grafo5, 0, 1) && !FiberConnection.verificarAristaConexion(grafo5, 0, 3));
	}
	
	@Test
	void eliminarPesoMayorAristaTestTrue() {
		
		grafo4.agregarConexion(0, 1, 0.333);
		grafo4.agregarConexion(1, 2, 100.0);
		grafo4.agregarConexion(2, 3, 7.799);
		
		grafo4.eliminarAristaConexionMayorVecinos();
		
		assertFalse(FiberConnection.verificarAristaConexion(grafo4, 0, 1));
	}
	
	@Test
	void eliminarPesoMayorAristaTestFalse() {
		
		grafo4.agregarConexion(0, 1, 10.0);
		
		grafo4.eliminarAristaConexionMayorVecinos();
		
		assertTrue(FiberConnection.verificarAristaConexion(grafo4, 0, 1));
	}
	
	@Test
	void arbolMinimoPrimTrue() {
		
		grafo4.agregarConexion(0, 1, 10.0);
		grafo4.agregarConexion(0, 2, 0.333);
		grafo4.agregarConexion(0, 3, 8.0);
		grafo4.agregarConexion(1, 2, 3.333);
		grafo4.agregarConexion(1, 3, 6.66);
		grafo4.agregarConexion(2, 3, 0.001);
		
		grafo4.arbolMinimoPrim();
		
		assertFalse(FiberConnection.verificarAristaConexion(grafo4, 0, 1));
	}
	
	@Test
	void arbolMinimoPrimFalse() {
		
		grafo3.agregarConexion(0, 1, 10.0);
		grafo3.agregarConexion(0, 2, 0.333);
		
		grafo3.arbolMinimoPrim();
		
		assertTrue(FiberConnection.verificarAristaConexion(grafo3, 0, 1));
	}
	
	@Test
	void resultadoMatriz() {
		
		grafo3.agregarConexion(0, 1, 10.0);
		
		assertEquals(grafo3.resultadoMatriz(), "Punto: 1 al Punto: 2 Distancia de: 10.0\n"
				+ "Punto: 2 al Punto: 1 Distancia de: 10.0\n");		
	}
	
	@Test
	void resultadoArbolMinimo() {
		
		grafo.agregarConexion(0, 1, 10.0);
		
		grafo.arbolMinimoPrim();
		
		assertEquals(grafo.resultadoArbolMinimo(), "");		
	}
}