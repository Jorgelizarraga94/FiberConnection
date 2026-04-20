package grafo;

public class Arista<T> {
	
	Nodo<T> destino;
	double peso;
	
	public Arista(Nodo<T> destino, double peso) {
		
		this.destino = destino;
		this.peso = peso;
	}

}
