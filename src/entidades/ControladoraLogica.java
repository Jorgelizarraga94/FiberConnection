package entidades;

import java.util.List;

import persistencia.ControladoraPersistencia;

public class ControladoraLogica {
	ControladoraPersistencia controladoraPersistencia = new ControladoraPersistencia();
	
	public void saveLocalidad(Double latitud, Double longitud, String palabra, String localidadNombre) {
		Localidad localidad = new Localidad(latitud, longitud, palabra, localidadNombre);
		
		controladoraPersistencia.guardarLocalidad(localidad);
	}
	
	public List<String> getLocalidades() {
		return controladoraPersistencia.traerLocalidades();
	}
	
	public String GetlocalidadNombre(int id) {
		return controladoraPersistencia.getLocalidad(id);
	}
	
	public void deleteSeleccionado(int seleccionado) {
		controladoraPersistencia.eliminarSeleccionado(seleccionado);
	}
}
