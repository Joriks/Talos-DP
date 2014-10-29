package Talos;

import java.util.Random;

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
	
	public void configurarPuerta(int[] llaves, int altura_puerta){
		this.altura_puerta = altura_puerta;
		guardarCombinacion(llaves);
		cerrarPuerta();
	}
		
	/**
	 * Guarda la combinacion de llaves en la combinación secreta.
	 * @param llaves
	 * PRE:
	 * POST:
	 * Complejidad:
	 */
	private void guardarCombinacion(int[] llaves) {
		volcarLlaves(llaves, 0, llaves.length-1);
	}
	
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
	private void cerrarPuerta() {
		cerradura = new Arbol<Llave>(combinacion_secreta);
		usadas = new Arbol<Llave>();
		estado = Estados.Cerrada;
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
				System.out.println("Llave: " + llave.getId() + " Correcta");
				usadas.insertar(llave);
				if(condicionApertura())
					abrirPuerta();
				return true;
			}
			usadas.insertar(llave);
			System.out.println("Llave: " + llave.getId() + " Incorrecta");
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
		if(estado == Estados.Cerrada)
			estado = Estados.Abierta;
	}
	
	public Estados estadoPuerta(){
		return estado;
	}
	
	@Override
	public String toString() {
		return ("(Puerta:" + estado + ":" + altura_puerta + ":" + cerradura.toString()+":"+usadas.toString()+")");
	}
	
	public static void main(String[] args) {
		
		System.out.println("--------Puerta 1--------");
		int[] comb = {1,3,5,7,9,11,13,15,17,19,21,23,25,27,29};		
		Puerta p = new Puerta(comb,2);
		
		p.probarLlave(new Llave(5));
		p.probarLlave(new Llave(5));
		p.probarLlave(new Llave(15));
		p.probarLlave(new Llave(15));
		
		System.out.println("--------Puerta 2--------");
		int[] comb2 = {1,3,4,6};
		Puerta p2 = new Puerta(comb2,3);
		
		p2.probarLlave(new Llave(1));
		if(p2.estadoPuerta() == Estados.Abierta)
			System.out.println("Puerta Abierta");
		else
			System.out.println("Puerta Cerrada");
		p2.probarLlave(new Llave(6));
		if(p2.estadoPuerta() == Estados.Abierta)
			System.out.println("Puerta Abierta");
		
		System.out.println("--------Puerta 3--------");
		int[] comb3 = {1,2,3,4,5,6,7,8,9,10,11};
		Puerta p3 = new Puerta(comb3,4);
		int i=0;
		
		while(p3.estadoPuerta() != Estados.Abierta && i < comb3.length){
			p3.probarLlave(new Llave(comb3[i]));
			i++;
		}
		if(p3.estadoPuerta() == Estados.Abierta)
			System.out.println("Puerta 3 Abierta tras probar " + i + " llaves de " + comb3.length);	
		
		System.out.println("--------Puerta 4--------");
		int[] comb4 = {2,4,5,7,6,1,8,9};
		Puerta p4 = new Puerta();
		System.out.println("Puerta: " + p4.estadoPuerta());
		System.out.println("configurando puerta con llaves 2,4,5,7,6,1,8,9 y altura de puerta 2");
		p4.configurarPuerta(comb4, 2);
		System.out.println("Puerta: " + p4.estadoPuerta());
		Random random = new Random(10);
		while(p4.estadoPuerta() != Estados.Abierta){
			p4.probarLlave(new Llave(random.nextInt(10)));
			System.out.println("Puerta: " + p4.estado);
		}
	}
}
