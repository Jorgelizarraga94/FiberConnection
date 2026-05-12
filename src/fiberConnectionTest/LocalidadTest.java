package fiberConnectionTest;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import entidades.Localidad;

public class LocalidadTest {

    private Localidad sanMiguel;
    private Localidad bellaVista;

    @Before
    public void setUp() {
        sanMiguel = new Localidad(-34.543159, -58.711774, "Buenos Aires", "San Miguel");
        bellaVista = new Localidad(-34.5333, -58.6667, "Buenos Aires", "Bella Vista");
    }

    @Test
    public void crearLocalidadTest() {
        assertNotNull(sanMiguel);
        assertEquals("San Miguel", sanMiguel.getNombre());
        assertEquals("Buenos Aires", sanMiguel.getProvincia());
    }

    @Test
    public void distanciaEntrePuntosTest() {
        double distancia = sanMiguel.distanciaEntreDosPuntos(bellaVista);

        assertTrue(distancia > 0);
    }

    @Test
    public void equalsLocalidadTest() {
        Localidad otra = new Localidad(-34.543159, -58.711774, "Buenos Aires", "San Miguel");

        assertEquals(sanMiguel, otra);
    }

    @Test
    public void hashCodeLocalidadTest() {
        Localidad otra = new Localidad(-34.543159, -58.711774, "Buenos Aires", "San Miguel");

        assertEquals(sanMiguel.hashCode(), otra.hashCode());
    }

    @Test
    public void setNombreTest() {
        sanMiguel.setNombre("Muñiz");

        assertEquals("Muñiz", sanMiguel.getNombre());
    }

    @Test
    public void setProvinciaTest() {
        sanMiguel.setProvincia("Cordoba");

        assertEquals("Cordoba", sanMiguel.getProvincia());
    }

    @Test
    public void toStringLocalidadTest() {
        String resultado = sanMiguel.toString();

        assertTrue(resultado.contains("San Miguel"));
        assertTrue(resultado.contains("Buenos Aires"));
    }
}