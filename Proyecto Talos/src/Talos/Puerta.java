package Talos;

import Estructuras.Arbol;

/**
 * Clase Puerta, guarda información sobre la cerradura de salida del laberinto.
 * @version 0.1 06/10/2014
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
		this.altura_puerta = altura_puerta;
		guardarCombinacion(llaves);
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
	 * Guarda la combinacion de llaves en la combinación secreta.
	 * @param llaves
	 */
	private void guardarCombinacion(int[] llaves) {
		//TODO
	}

	public static void main(String[] args) {
		int[] comb = {1,3,5,7,9,11,13,15,17,19,21,23,25,27,29};
		Puerta p = new Puerta(comb,2);
	}
}
