package fiberConnectionTest;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import entidades.Conexion;
import entidades.Localidad;

public class ConexionTest {

    private Localidad sanMiguel;
    private Localidad bellaVista;
    private Localidad rosario;

    @Before
    public void setUp() {
        sanMiguel = new Localidad(-34.543159, -58.711774, "Buenos Aires", "San Miguel");
        bellaVista = new Localidad(-34.5333, -58.6667, "Buenos Aires", "Bella Vista");
        rosario = new Localidad(-32.949519, -60.681542, "Santa Fe", "Rosario");
    }

    @Test
    public void crearConexionTest() {
        Conexion conexion = new Conexion(sanMiguel, bellaVista, 15.0);

        assertNotNull(conexion);
        assertEquals(sanMiguel, conexion.getOrigen());
        assertEquals(bellaVista, conexion.getDestino());
        assertEquals(15.0, conexion.getKm(), 0.01);
    }

    @Test
    public void calcularCostoMismaProvinciaTest() {
        Conexion conexion = new Conexion(sanMiguel, bellaVista, 10.0);

        double esperado = 10.0 * 20000.0;

        assertEquals(esperado, conexion.getCostoBaseConexion(), 0.01);
    }

    @Test
    public void calcularCostoDistintaProvinciaTest() {
        Conexion conexion = new Conexion(sanMiguel, rosario, 10.0);

        double esperado = (10.0 * 20000.0) + 50000.0;

        assertEquals(esperado, conexion.getCostoBaseConexion(), 0.01);
    }

    @Test
    public void equalsConexionTest() {
        Conexion conexion1 = new Conexion(sanMiguel, bellaVista, 10.0);
        Conexion conexion2 = new Conexion(bellaVista, sanMiguel, 20.0);

        assertEquals(conexion1, conexion2);
    }

    @Test
    public void hashCodeConexionTest() {
        Conexion conexion1 = new Conexion(sanMiguel, bellaVista, 10.0);
        Conexion conexion2 = new Conexion(bellaVista, sanMiguel, 20.0);

        assertEquals(conexion1.hashCode(), conexion2.hashCode());
    }

    @Test
    public void toStringConexionTest() {
        Conexion conexion = new Conexion(sanMiguel, bellaVista, 10.0);

        assertEquals("San Miguel - Bella Vista", conexion.toString());
    }
}