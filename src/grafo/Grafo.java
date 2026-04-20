package grafo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Grafo<T> {
	
	
	private Arista<T>[] aristas;
	
	
	List<T> vertices = new ArrayList<>();
	List<Arista<T>> aristasList = new ArrayList<>();
	
	// Mapa de adyacencia para representar el grafo
	Map<T, List<Arista<T>>> adyacencia = new HashMap<>();
	
	List<Nodo<T>> nodos = new ArrayList<>();
	
	public Nodo<T> agregarNodo(T valor){
		Nodo<T> nodo = new Nodo<>(valor);
		nodos.add(nodo);
		return nodo;
	}
	
	public void agregarArista(Nodo<T> origen, Nodo<T> destino, double peso) {
		
		return;
		
	}

	public Arista<T>[] getAristas() {
		return aristas;
	}

	public void setAristas(Arista<T>[] aristas) {
		this.aristas = aristas;
	}
	
	
	
	

}
