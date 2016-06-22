package Talos;

import Estructuras.Arbol;

/**
 * Clase Puerta, guarda información sobre la cerradura de salida del laberinto.
 * @version 0.2 06/10/2014
 * @author Grupo Talos { Jorge Bote Albalá, Juan Jose Ramón Rodríguez }
 */
public class Puerta {
	
	/** Guarda el estado de la puerta */
	private Estados estado;
	
	/** Altura para la que se abrirá la cerradura */
	private int altura_puerta;
	
	/** Combinacion de la cerradura */
	private Arbol<Llave> combinacion_secreta;
	
	/** Estado actual del arbol de cerradura */
	private Arbol<Llave> cerradura;
	
	/** Arbol de llaves usadas en la puerta */
	private Arbol<Llave> usadas;
	
	public Puerta() {
		estado = Estados.NoConfigurada;
		altura_puerta = 0;
		iniciarArboles();
	}
	
	public Puerta(int[] llaves, int altura_puerta){
		iniciarArboles();
		configurarPuerta(llaves, altura_puerta);
	}
	
	/**
	 * Inicializa los arboles de las cerraduras
	 * PRE:
	 * POST:
	 * Complejidad: O(1);
	 */
	private void iniciarArboles() {
		combinacion_secreta = new Arbol<Llave>();
		cerradura = new Arbol<Llave>();
		usadas = new Arbol<Llave>();
	}
	
	/**
	 * Configura la puerta con la altura y las llaves dadas.
	 * @param llaves, conjunto de llaves que formaran parte de la combinacion.
	 * @param altura_puerta, altura del arbol a la que se abrira
	 * Complejidad:O(n)
	 */
	public void configurarPuerta(int[] llaves, int altura_puerta){
		this.altura_puerta = altura_puerta;
		guardarCombinacion(llaves);
		cerrarPuerta();
	}
		
	/**
	 * Guarda la combinacion de llaves en la combinación secreta.
	 * @param llaves, conjunto de llaves que formaran parte de la combinacion.
	 * Complejidad:O(n)
	 */
	private void guardarCombinacion(int[] llaves) {
		volcarLlaves(llaves, 0, llaves.length-1);
	}
	
	/**
	 * Vuelca las llaves en el arbol de combinacion secreta
	 * @param llaves, conjunto de llaves
	 * @param izq, ultima posicion izquierda
	 * @param dch, ultima posicion derecha
	 * Complejidad:O(n)
	 */
	private void volcarLlaves(int[] llaves, int izq, int dch) {
		int medio = (izq + dch)/2;
		if(medio >= izq && medio <= dch){
			combinacion_secreta.insertar(new Llave(llaves[medio]));
			volcarLlaves(llaves, izq, medio-1);
			volcarLlaves(llaves, medio+1, dch);
		}
	}

	/**
	 * Cambia el estado de la puerta a Estados.Cerrada y regenera la cerradura de la puerta
	 * PRE:
	 * POST:
	 * Complejidad: O(1)
	 */
	public void cerrarPuerta() {
		cerradura = new Arbol<Llave>(combinacion_secreta);
		usadas = new Arbol<Llave>();
		estado = Estados.cerrada;
	}
	
	/**
	 * Comprueba si una llave pertenece a la cerradura.
	 * @param llave, llave que vamos a utilizar en la cerradura. 
	 * @return devuelve true si la llave es correcta, false en caso contrario
	 * PRE:
	 * POST:
	 * Complejidad: O(log n)
	 */
	public boolean probarLlave(Llave llave){
		if(!usadas.pertenece(llave)){
			if(cerradura.pertenece(llave)){
				cerradura.borrar(llave);
//				System.out.println("Llave: " + llave.getId() + " Correcta");
				usadas.insertar(llave);
				if(condicionApertura())
					abrirPuerta();
				return true;
			}
			usadas.insertar(llave);
//			System.out.println("Llave: " + llave.getId() + " Incorrecta");
		}
		else
			System.out.println("ALARMA: Llave: " + llave.getId() + " ya probada");
		return false;
	}
	
	/**
	 * Comprueba si se cumplen las condiciones de apertura de la cerradura
	 * @return true si se cumplen, false en caso contrario.
	 */
	private boolean condicionApertura() {
		if(cerradura.profundidad() < altura_puerta && 
				cerradura.nodosInternos() >= cerradura.nodosHoja())
			return true;
		return false;
	}

	/**
	 * Cambia el estado de la cerradura de la puerta a Abierta, si la puerta está cerrada
	 * y configurada.
	 */
	private void abrirPuerta() {
		if(estado == Estados.cerrada)
			estado = Estados.abierta;
	}
	
	/**
	 * Metodo que devuelve el estado de la puerta
	 * @return estado
	 */
	public Estados estadoPuerta(){
		return estado;
	}
	
	@Override
	public String toString() {
		return ("(puerta:" + estado + ":" + altura_puerta + ":" + cerradura.toString()+":"+usadas.toString()+")");
	}
}
