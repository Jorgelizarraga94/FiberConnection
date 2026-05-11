package fiberConnectionTest;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import entidades.Conexion;
import entidades.Localidad;
import grafo.Grafo;
import servicio.FiberConnection;
import logica.AlgoritmoAGM;
public class AGMTest {
    private List<Localidad> localidadesTest;
    private Localidad sanMiguel = new Localidad(-34.543159, -58.711774, "San Miguel", "Buenos Aires");
    private Localidad bellaVista = new Localidad(-34.5333, -58.6667, "Bella Vista", "Buenos Aires");
    private Localidad joseCPaz = new Localidad(-34.51541, -58.76813, "Jose C Paz", "Buenos Aires");
    @Before
    public void setUp() {
        localidadesTest = new ArrayList<>();
    }
    @Test
    public void crearGrafoTest() {
        localidadesTest.add(sanMiguel);
        localidadesTest.add(bellaVista);
        List<Localidad> resultado = FiberConnection.construirGrafoTest(localidadesTest);
        assertNotNull(resultado);
        assertEquals("El grafo debería tener 2 localidades", 2, resultado.size());
    }
    @Test
    public void crearGrafoTestVacio() {
        List<Localidad> vacia = new ArrayList<>();
        List<Localidad> resultado = FiberConnection.construirGrafoTest(vacia);
        assertEquals(0, resultado.size());
    }
    @Test
    public void conectadoTest() {
        localidadesTest.add(sanMiguel);
        localidadesTest.add(bellaVista);
        List<Localidad> grafoListado = FiberConnection.construirGrafoTest(localidadesTest);
        assertTrue(FiberConnection.estaConectado(grafoListado, sanMiguel, bellaVista));
    }
    @Test
    public void resultadoArbolMinimoTest() {
        Grafo g = new Grafo();
        g.agregarConexion(sanMiguel, bellaVista, 10.0);
        g.agregarConexion(bellaVista, joseCPaz, 5.0);
        g.agregarConexion(sanMiguel, joseCPaz, 50.0);
        AlgoritmoAGM algoritmo = new AlgoritmoAGM();
        Grafo agm = algoritmo.generarAGM(g);
        FiberConnection servicio = new FiberConnection();
        assertEquals(2, servicio.obtenerAristasUnicas(agm).size());
    }
}
