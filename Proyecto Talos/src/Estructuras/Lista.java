package Estructuras;

/**
 * Clase genérica Lista
 * @version 0.1 10/10/2014
 * @author Grupo Talos { Jorge Bote Albalá, Juan Jose Ramón Rodríguez }
 * @param T, tipo de dato genérico que hereda de la clase Comparable.
 */
public class Lista<T extends Comparable<T>>{

	private Nodo primero;
	private Nodo ultimo;

	private class Nodo{
		private T datoList;
		private Nodo suce;
		private Nodo prev;

		Nodo (){
			datoList = null;
			suce = null;
			prev = null;
		}
	}
	public Lista(){
		primero = null;
		ultimo = null;
	}

	public void link (Nodo a, Nodo b){
		a.suce = b;
		b.prev = a;
	}

	public boolean vacia(){
		return (primero == null);
	}

	public void insertarInicio(T tipoDato){
		Nodo aux = new Nodo ();
		aux.datoList = tipoDato;
		if(vacia()){
			primero = aux;
			ultimo = primero;
		}
		else{
			primero.prev = aux;
			aux.suce= primero;
			primero= aux;
			link(ultimo,aux);
		}
	}

	public void insertarFinal (T tipoDato){
		Nodo aux = new Nodo();
		aux.datoList = tipoDato;
		if(vacia()){
			primero = aux;
			ultimo = aux;
		}
		else{
			ultimo.suce = aux;
			aux.prev = ultimo;
			ultimo = aux;
			link (ultimo, primero);

		}

	}
	public void eliminarInicio(){
		if(!vacia()){
			Nodo p = primero.suce;
			if(p==null){
				primero = null;
				ultimo = null;
			}
			else{
				p.prev = null;
				primero = p;
			}
		}
	}

	public void eliminarFinal(){
		if (!vacia()){
			Nodo u = ultimo.prev;
			if(u == null){
				primero = null;
				ultimo = null;
			}
			else{
				u.suce = null;
				ultimo = u;
			}
		}
	}

	public Nodo buscar (T tipoDato){
		Nodo buscado = null;
		Nodo iterador = primero;

		while(buscado == null && iterador !=null){
			if(iterador.datoList.compareTo(tipoDato)==0){
				buscado = iterador;
			}
			iterador = iterador.suce;
		}
		return buscado;
	}

}