package fiberConnectionTest;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import entidades.Localidad;
import persistencia.ControladoraPersistencia;

public class ControladoraPersistenciaTest {

    private ControladoraPersistencia persistencia;

    @Before
    public void setUp() {
        persistencia = new ControladoraPersistencia();
    }

    @Test
    public void guardarLocalidadTest() {

        Localidad localidad = new Localidad(-34.60, -58.38, "Buenos Aires","Capital Federal");

        persistencia.createLocalidad(localidad);

        List<String> localidades = persistencia.findAllLocalidades();

        assertNotNull(localidades);
        assertFalse(localidades.isEmpty());
    }

    @Test
    public void obtenerLocalidadesTest() {

        List<String> localidades = persistencia.findAllLocalidades();

        assertNotNull(localidades);
    }

    @Test
    public void buscarLocalidadPorIndiceTest() {

        List<String> localidades = persistencia.findAllLocalidades();

        if (!localidades.isEmpty()) {

            String localidad = persistencia.findLocalidad(0);

            assertNotNull(localidad);
        }
    }

    @Test
    public void eliminarLocalidadTest() {

        List<String> antes = persistencia.findAllLocalidades();

        if (!antes.isEmpty()) {

            persistencia.deleteLocalidadSeleccionada(0);

            List<String> despues = persistencia.findAllLocalidades();

            assertTrue(despues.size() <= antes.size());
        }
    }
}