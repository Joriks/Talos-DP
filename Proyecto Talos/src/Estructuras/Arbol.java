package Estructuras;


/**
 * Clase genérica Arbol
 * @version 0.1 06/10/2014
 * @author Grupo Talos { Jorge Bote Albalá, Juan Jose Ramón Rodríguez }
 * @param T, tipo de dato genérico que implementa la clase Comparable.
 */
public class Arbol<T extends Comparable<T>> {

	/** Dato almacenado en cada nodo del arbol. */
	private T datoRaiz;

	/** Hijo izquierdo del nodo actual */
	private Arbol<T> hIzq;

	/** Hijo derecho del nodo actual */
	private Arbol<T> hDer;

	/** Atributo que indica si el arbol esta vacio.	 */
	private boolean esVacio;
	
	/** Metodos de la Clase */
	public Arbol() {
		this.esVacio = true;
		this.hIzq = null;
		this.hDer = null;
	}

	public Arbol(Arbol<T> hIzq, T datoRaiz, Arbol<T> hDer) {
		this.datoRaiz = datoRaiz;
		this.hIzq = hIzq;
		this.hDer = hDer;
		if(this.datoRaiz != null)//¿es necesario?
			esVacio= false;
	}
	
	public Arbol(Arbol<T> a){
		this.datoRaiz = a.datoRaiz;
		if(a.hIzq != null)
			this.hIzq = new Arbol<T>(a.hIzq);
		if(a.hDer != null)
			this.hDer = new Arbol<T>(a.hDer);
	}

	public Arbol<T> getHijoIzq() {
		return hIzq;
	}

	public Arbol<T> getHijoDer() {
		return hDer;
	}

	public T getRaiz() {
		return datoRaiz;
	}

	/**
	 * Comprueba si el arbol esta vacio.
	 * PRE: 
	 * POST:
	 * Complejidad: O(1)
	 * @return verdadero si el arbol esta vacio, falso en caso contrario
	 */
	public boolean vacio() {
		return esVacio;
	}

	/**
	 * Inserta un nuevo dato en el arbol.
	 * PRE: dato es tipodato generico no nulo.
	 * POST: arbol = arbol + dato
	 * Complejidad: O(log n)
	 * @param dato, el dato genérico a insertar en el arbol
	 * @return verdadero si el dato se ha insertado correctamente, falso en caso contrario
	 */
	public boolean insertar(T dato) {
		boolean resultado = true;
		if (vacio()) {
			datoRaiz = dato;
			esVacio = false;
		}
		else {
			if (!(this.datoRaiz.equals(dato))) {
				Arbol<T> aux;
				if (dato.compareTo(this.datoRaiz) < 0) {
					if ((aux = getHijoIzq()) == null)
						aux = new Arbol<T>();
					hIzq = aux;
				} else {
					if ((aux = getHijoDer()) == null)
						aux = new Arbol<T>();
					hDer = aux;
				}
				resultado = aux.insertar(dato);
			} else
				resultado = false;
		}
		return resultado;
	}

	/**
	 * Comprueba si un dato se encuentra almacenado en el arbol
	 * PRE: dato es tipodato generico no nulo.
	 * POST:
	 * Complejidad: O(log n)
	 * @param dato, el dato a buscar
	 * @return verdadero si el dato se encuentra en el arbol, falso en caso contrario
	 */
	public boolean pertenece(T dato) {
		Arbol<T> aux = null;
		boolean encontrado = false;
		if (!vacio()) {
			if (this.datoRaiz.equals(dato))
				encontrado = true;
			else {
				if (dato.compareTo(this.datoRaiz) < 0)
					aux = getHijoIzq();
				else
					aux = getHijoDer();
				if (aux != null)
					encontrado = aux.pertenece(dato);
			}
		}
		return encontrado;
	}

	/**
	 * Borrar un dato del arbol.
	 * PRE: T es tipodato generico no nulo.
	 * POST: arbol = arbol - T.
	 * Complejidad:O(log n)
	 * @param dato, el dato que se quiere borrar.
	 */
	public void borrar(T dato){
		if (!vacio()) {
			if (dato.compareTo(this.datoRaiz) < 0) {
				if (hIzq != null)
					hIzq = hIzq.borrar_orden(dato);
			} else if (dato.compareTo(this.datoRaiz) > 0) {
				if (hDer != null)
					hDer = hDer.borrar_orden(dato);
			} else
			{
				if (hIzq == null && hDer == null) {
					esVacio = true;
				} else
					borrar_orden(dato);
			}
		}
	}

	/**
	 * Borrar un dato. Este metodo es utilizado por el metodo borrar anterior.
	 * PRE: T es tipodato generico no nulo.
	 * POST: arbol = arbol - T.
	 * Complejidad:O(log n)
	 * @param dato, el dato a borrar.
	 * @return Devuelve el arbol resultante despues de haber realizado el borrado.
	 */
	private Arbol<T> borrar_orden(T dato) {
		T datoaux;
		Arbol<T> retorno = this;
		Arbol<T> aborrar, candidato, antecesor;

		if (!vacio()) {
			if (dato.compareTo(this.datoRaiz) < 0) {
				if (hIzq != null)
					hIzq = hIzq.borrar_orden(dato);

			} else if (dato.compareTo(this.datoRaiz) > 0) {
				if (hDer != null)
					hDer = hDer.borrar_orden(dato);
			} else {
				aborrar = this;
				if ((hDer == null) && (hIzq == null)) {
					retorno = null;
				} else {
					if (hDer == null) {
						aborrar = hIzq;
						datoaux = this.datoRaiz;
						datoRaiz = hIzq.getRaiz();
						hIzq.datoRaiz = datoaux;
						hIzq = hIzq.getHijoIzq();
						hDer = aborrar.getHijoDer();

						retorno = this;
					} else if (hIzq == null) {
						aborrar = hDer;
						datoaux = datoRaiz;
						datoRaiz = hDer.getRaiz();
						hDer.datoRaiz = datoaux;
						hDer = hDer.getHijoDer();
						hIzq = aborrar.getHijoIzq();

						retorno = this;
					} else {
						candidato = this.getHijoIzq();
						antecesor = this;
						while (candidato.getHijoDer() != null) {
							antecesor = candidato;
							candidato = candidato.getHijoDer();
						}

						datoaux = datoRaiz;
						datoRaiz = candidato.getRaiz();
						candidato.datoRaiz = datoaux;
						aborrar = candidato;
						if (antecesor == this)
							hIzq = candidato.getHijoIzq();
						else
							antecesor.hDer = candidato.getHijoIzq();
					}
					aborrar.hIzq = null;
					aborrar.hDer = null;
				}
			}
		}
		return retorno;
	}

	/**
	 * Recorrido inOrden del arbol.
	 * PRE: 
	 * POST:
	 * Complejidad: O(n)
	 */
	public void inOrden() {
		Arbol<T> aux = null;
		if (!vacio()) {
			if ((aux = getHijoIzq()) != null) {
				aux.inOrden();
			}

			System.out.print(this.datoRaiz.toString() + " ");

			if ((aux = getHijoDer()) != null) {
				aux.inOrden();
			}
		} else
			System.out.println("El arbol esta vacio");

	}
	
	/**
	 * Recorrido preOrden del arbol.
	 * PRE: 
	 * POST:
	 * Complejidad: O(n)
	 */
	public void preOrden() {
		Arbol<T> aux = null;
		if (!vacio()) {
			System.out.print(this.datoRaiz.toString() + " ");
			if ((aux = getHijoIzq()) != null) {
				aux.preOrden();
			}

			if ((aux = getHijoDer()) != null) {
				aux.preOrden();
			}
		}
	}

	/**
	 * Recorrido postOrden del arbol.
	 * PRE: 
	 * POST:
	 * Complejidad: O(n)
	 */
	public void postOrden() {
		Arbol<T> aux = null;
		if (!vacio()) {
			if ((aux = getHijoIzq()) != null) {
				aux.postOrden();
			}
			if ((aux = getHijoDer()) != null) {
				aux.postOrden();
			}
			System.out.print(this.datoRaiz.toString() + " ");
		}
	}

	/**
	 * Profundidad del arbol
	 * PRE: 
	 * POST:
	 * Complejidad: O(n)
	 * @return int, entero que devuelve la profundidad máxima del arbol.
	 */
	public int profundidad() {
		int dcha = 0;
		int izda = 0;
		Arbol<T> aux = this;
		if (!vacio()) {
			if ((aux = getHijoIzq()) != null) {
				izda = 1 + aux.profundidad();
			}
			if ((aux = getHijoDer()) != null) {
				dcha = 1 + aux.profundidad();

			}
			if ((getHijoDer() == null) && (getHijoIzq() == null))
				return 1;
			if (izda > dcha)
				return izda;
			else
				return dcha;

		}
		return 0;

	}
	
	/**
	 * Comprueba si el dato insertado es un nodo hoja.
	 * PRE:
	 * POST:
	 * Complejidad: O(n)
	 * @param dato, Tipodato T
	 * @return devuelve true si hoja, false en caso contrario
	 */
	public boolean esHoja(T dato){
		switch (dato.compareTo(this.datoRaiz)) {
		case 0:
			return ((hIzq == null && hDer == null) ? true : false);
		case -1:
			return (hIzq != null) ? hIzq.esHoja(dato) : false;
		case 1:
			return (hDer != null) ? hDer.esHoja(dato) : false;
		default:
			return false;
		}
	}

	
	/**
	 * Numero de nodos hojas.
	 * PRE: 
	 * POST:
	 * Complejidad: O(n)
	 * @return int, entero que devuelve el numero de hojas que tiene el arbol.
	 */
	public int nodosHoja(){
		Arbol<T> aux = null;
		int nodos_hoja=0;
		if (!vacio()){
			if ((aux = getHijoIzq()) != null){
				nodos_hoja = nodos_hoja + aux.nodosHoja();
			}
			if ((aux = getHijoDer()) != null){
				nodos_hoja = nodos_hoja + aux.nodosHoja();
			}
			if (((aux = getHijoDer()) == null) && ((aux = getHijoIzq()) == null)){
				nodos_hoja++;
				return nodos_hoja;
			}
			else
				return nodos_hoja;
		}
		else
			return nodos_hoja;
	}
	
	/**
	 * Numero de nodos internos.
	 * PRE: 
	 * POST:
	 * Complejidad: O(n)
	 * @return int, entero que devuelve el numero de ramas que tiene el arbol.
	 */
	public int nodosInternos(){
		Arbol<T> aux = null;
		int nodos_internos=0;
		if (!vacio()){
			if ((aux = getHijoIzq()) != null){
				nodos_internos = nodos_internos + aux.nodosInternos();
			}
			if ((aux = getHijoDer()) != null){
				nodos_internos = nodos_internos + aux.nodosInternos();
			}
			if (((aux = getHijoDer()) != null) || ((aux = getHijoIzq()) != null)){
				nodos_internos++;
				return nodos_internos;
			}
			else
				return nodos_internos;
		}
		else
			return 0;
	}
	
	/**
	 * Devuelve el arbol como un String
	 * PRE: 
	 * POST:
	 * Complejidad: O(n)
	 * @return String, cadena que devuelve con postorden todos los identificadores de los objetos dentro del arbol.
	 */
	public String toString(){
		String stringTreeIzq = "";
		String stringTreeDer = "";
		if (datoRaiz != null) {
			if (hIzq != null) {
				stringTreeIzq = this.hIzq.toString() + " ";
			}
			if (hDer != null) {
				stringTreeDer = " " + this.hDer.toString();
			}
			return stringTreeIzq + this.datoRaiz.toString() + stringTreeDer;
		}
		return "";
	}
}