package entidades;

import java.util.List;

import persistencia.ControladoraPersistencia;

public class ControladoraLogica {
	ControladoraPersistencia controladoraPersistencia = new ControladoraPersistencia();
	
	public void saveLocalidad(Double latitud, Double longitud, String palabra, String localidadNombre) {
		Localidad localidad = new Localidad(latitud, longitud, palabra, localidadNombre);
		
		controladoraPersistencia.createLocalidad(localidad);
	}
	
	public List<String> getLocalidades() {
		return controladoraPersistencia.findAllLocalidades();
	}
	
	public String GetlocalidadNombre(int id) {
		return controladoraPersistencia.findLocalidad(id);
	}
	
	public void deleteSeleccionado(int seleccionado) {
		controladoraPersistencia.deleteLocalidadSeleccionada(seleccionado);
	}
	public Localidad convertirListaAobjetoLocalidad(String linea){
		String[] partes = linea.split(",");

		Localidad loc = new Localidad(Double.parseDouble(partes[0]), Double.parseDouble(partes[1]), partes[2],
				partes[3]);
		return loc;
	}
}
