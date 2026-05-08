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



public class BaseDeDatos {
	// ================== OBTENER ARCHIVO ==================
    private static File getArchivo() {
        File archivo = new File("Datos.txt");
        return archivo;
    }

    // ================== GUARDAR ==================
    public static void guardarDatos(Double latitud, Double longitud, String provincia, String localidad) {
        File archivo = getArchivo();

        try (FileWriter fw = new FileWriter(archivo, true)) {

            fw.write(latitud + "," + longitud + "," + provincia + "," + localidad + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ================== LEER ==================
    public static List<String> leerArchivo() {
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

    // ================== ORDENAR ==================
    public static List<String> getDatosOrdenados() {
    	
        List<String> datos = leerArchivo();

        datos.sort((a, b) -> {
            String[] datosA = a.split(",");
            String[] datosB= b.split(",");
            
            Double longitudA = Double.parseDouble(datosA[0]);
            Double longitudB = Double.parseDouble(datosB[0]);

            return Double.compare(longitudA,longitudB); 
        });

        return datos;
    }

    

    public static void eliminarSeleccionado(int selectedRow) {
        File archivo = getArchivo();
        List<String> lineasRestantes = new ArrayList<>();

        try {
            // 1. Leer todas las líneas del archivo
            List<String> todasLasLineas = Files.readAllLines(archivo.toPath());

            // 2. Validar que el índice sea correcto
            if (selectedRow >= 0 && selectedRow < todasLasLineas.size()) {
                // Removemos la línea que coincide con la fila seleccionada
                todasLasLineas.remove(selectedRow);
                
                // 3. Sobreescribir el archivo con la lista actualizada
                Files.write(archivo.toPath(), todasLasLineas, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

