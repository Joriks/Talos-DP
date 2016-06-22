package Estructuras;

/**
 * Clase genérica Pila
 * @version 0.1 10/10/2014
 * @author Grupo Talos { Jorge Bote Albalá, Juan Jose Ramón Rodríguez }
 * @param T, tipo de dato genérico que hereda de la clase Comparable.
 * Contiene una clase anidada que guarda el objeto genérico y su sucesor.
 */
public class Pila<T extends Comparable<T>>{

	private Nodo Top;

	private class Nodo{
		private T datoN;
		private Nodo suce;

		Nodo (){
			datoN = null;
			suce = null;
		}
	}

	public Pila(){
		Top = null;
	}

	public void apilar(T datoPila){
		Nodo aux = new Nodo();
		aux.datoN = datoPila;
		aux.suce = Top;
		Top = aux;
	}

	public boolean desapilar(){
		if(!empty()){
			Top = Top.suce;
			return true;
		}
		return false;
	}

	public T getTop(){
		return Top.datoN;
	}

	public boolean empty(){
		return (Top.datoN == null);
	}

}
