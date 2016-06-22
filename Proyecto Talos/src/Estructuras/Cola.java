package Estructuras;

/**
 * Clase genérica Cola
 * @version 0.1 10/10/2014
 * @author Grupo Talos { Jorge Bote Albalá, Juan Jose Ramón Rodríguez }
 * @param T, tipo de dato genérico que hereda de la clase Comparable.
 */
public class Cola<T extends Comparable<T>>{
	private Nodo Primero;
	private Nodo Ultimo;

	private class Nodo{
		private T datoN;
		private Nodo suce;

		Nodo (){
			datoN = null;
			suce = null;
		}
	}
	public Cola(){
		Primero = null;
		Ultimo = null;
	}

	public void encolar(T datoCola){
		Nodo aux = new Nodo();
		aux.datoN = datoCola;
		aux.suce = Ultimo;
		Ultimo = aux;
	}

	public boolean desencolar(){
		if(vacia())
			return false;
		Primero = Primero.suce;
		return true;
	}

	public boolean vacia(){
		return (Primero.datoN == null);
	}

	public T consultar(){
		return Primero.datoN;
	}
}
