package persistencia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import entidades.Localidad;


public class ControladoraPersistencia {
	// ================== OBTENER ARCHIVO ==================
    private static File getArchivo() {
        File archivo = new File("Datos.txt");
        return archivo;
    }

    // ================== GUARDAR ==================
    public void createLocalidad(Localidad localidad) {
        File archivo = getArchivo();

        try (FileWriter fw = new FileWriter(archivo, true)) {

            fw.write(localidad.getLatitud() + "," + localidad.getLongitud() + "," + localidad.getProvincia() + "," + localidad.getNombre() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ================== BUSCAR LOCALIDADES ==================
    public List<String> findAllLocalidades() {
        List<String> lista = new ArrayList<>();
        File archivo = getArchivo();
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                lista.add(linea);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return lista;
    }
    //=================== ELIMINAR SELECCIONADO =====================
    public void deleteLocalidadSeleccionada(int seleccionada) {
        File archivo = getArchivo();
        List<String> lineasRestantes = new ArrayList<>();
        try {
            List<String> todasLasLineas = Files.readAllLines(archivo.toPath());
            if (seleccionada >= 0 && seleccionada < todasLasLineas.size()) {
                todasLasLineas.remove(seleccionada);
                Files.write(archivo.toPath(), todasLasLineas, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //==================== BUSCAR LOCALIDAD POR INDICE =========================
	public String findLocalidad(int indice) {
		List<String> localidadStrings = findAllLocalidades();
		String localidadNombre = localidadStrings.get(indice);
		return localidadNombre;
	}
}

