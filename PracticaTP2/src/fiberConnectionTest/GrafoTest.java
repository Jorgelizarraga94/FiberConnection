package fiberConnectionTest;
import static org.junit.Assert.*;
import java.util.HashMap;
import java.util.Set;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import entidades.Localidad;
import grafo.Grafo;
import servicio.FiberConnection;
public class GrafoTest {
    private Localidad sanMiguel = new Localidad(-34.543159, -58.711774, "San Miguel", "Buenos Aires");
    private Localidad bellaVista = new Localidad(-34.5333, -58.6667, "Bella Vista", "Buenos Aires");
    private Localidad joseCPaz = new Localidad(-34.51541, -58.76813, "Jose C Paz", "Buenos Aires");
    private Grafo grafo;
    private Grafo grafo4;
    @Before
    public void init() {
        grafo = new Grafo();
        grafo4 = new Grafo();
    }
    @Test
    public void agregarYVerificarTamanioTest() {
        grafo.agregarConexion(sanMiguel, bellaVista, 10.5);
        assertEquals(2, FiberConnection.tamanio(grafo));
    }
    @Test
    public void existeAristaTestTrue() {
        grafo.agregarConexion(sanMiguel, bellaVista, 1.0);
        assertTrue(FiberConnection.verificarAristaConexion(grafo, sanMiguel, bellaVista));
    }
    @Test
    public void existeAristaTestFalse() {
        grafo.agregarConexion(sanMiguel, bellaVista, 1.0);
        assertFalse(FiberConnection.verificarAristaConexion(grafo, sanMiguel, joseCPaz));
    }
    @Test
    public void calcularPresupuestoTest() {
        Grafo agm = new Grafo();
        agm.agregarConexion(sanMiguel, bellaVista, 100.0);
        FiberConnection servicio = new FiberConnection();
        Double presupuesto = servicio.calcularPresupuesto(agm);
        assertNotNull(presupuesto);
        assertTrue(presupuesto >= 0);
    }
    @Test
    public void vecinosTest() {
        grafo4.agregarConexion(sanMiguel, bellaVista, 0.0);
        grafo4.agregarConexion(sanMiguel, joseCPaz, 0.0);
        Set<Localidad> vecinos = FiberConnection.verificarVecinos(grafo4, sanMiguel);
        
        assertNotNull(vecinos);
        int cantidadVecinos = vecinos.size(); 
        assertEquals(2, cantidadVecinos);
    }

    @Test
    public void obtenerPesoTest() {
        grafo4.agregarConexion(sanMiguel, bellaVista, 10.0);
        assertEquals(10, grafo4.obtenerPeso(sanMiguel, bellaVista));
    }
    @Test
    public void eliminarAristaTest() {
        grafo4.agregarConexion(sanMiguel, bellaVista, 0.0);
        grafo4.eliminarConexion(sanMiguel, bellaVista);
        assertFalse(FiberConnection.verificarAristaConexion(grafo4, sanMiguel, bellaVista));
    }
}