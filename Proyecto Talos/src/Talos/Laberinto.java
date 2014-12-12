package Talos;

import java.util.HashMap;
import java.util.Map.Entry;

import Robots.Robot;

/**
 * Clase Laberinto, guarda las salas del mapa y ejecuta la simulacion
 * @version 0.2 30/10/2014
 * @author Grupo Talos { Jorge Bote Albalá, Juan Jose Ramón Rodríguez }
 */
public class Laberinto {

	/** Guarda el ancho de laberinto */
	private int ancho;
	
	/** Guarda el alto de laberinto */
	private int alto;
	
	/** Guarda la altura del árbol a partir de la cual se abre la puerta del laberinto */
	private int altura_arbol;
	
	/** Identificador de la sala donde está la puerta de salida del laberint o*/
	private int sala_puerta;
	
	/** Identificador de la sala de victoria donde se almacenan los robots que consiguen salir del laberinto */
	public static final int sala_ganadores = 1111;
	
	/** HashMap con la información de las salas del laberinto */
	private HashMap<Integer, Sala> salas;
	
	/** Número máximo de turnos de una simulación del laberinto */
	private int max_turnos;
	
	/** Turno actual de la simulación */
	private int turno_actual;
	
	/** Laberinto siguiendo el patrón Singleton*/
	private static Laberinto instancia;
	
	/**
	 * Constructor por defecto de la clase Laberinto
	 * PRE:
	 * POST: Crea un laberinto con los valores por defecto (vacío);
	 * Complejidad: O(1);
	 */
	private Laberinto(){
		ancho = 0;
		alto = 0;
		altura_arbol = 0;
		sala_puerta = 0;
		salas = new HashMap<Integer, Sala>();
		max_turnos = 0;
	}
	
	/**
	 * Obtener la instancia del laberinto
	 * PRE: 
	 * POST: Devuelve la instancia, de tipo estático, del laberinto
	 * @return Laberinto
	 * Complejidad: O(1);
	 */
	public static Laberinto getInstancia(){
		if(instancia == null)
			instancia = new Laberinto();
		return instancia;
	}
	
	/**
	 * Obtener el ancho del laberinto
	 * PRE: El laberinto debe estar creado correctamente
	 * POST: Devuelve el ancho del laberinto
	 * @return int
	 * Complejidad: O(1);
	 */
	public int getAncho() {
		return ancho;
	}

	/**
	 * Obtener el alto del laberinto
	 * PRE: El laberinto debe estar creado correctamente
	 * POST: Devuelve el alto del laberinto
	 * @return int
	 * Complejidad: O(1);
	 */
	public int getAlto() {
		return alto;
	}
	
	/**
	 * Metodo para configurar el laberinto inicial.
	 * PRE: El laberinto debe estar creado y los parámetros ser correctos
	 * POST: Configura el laberinto con los datos 
	 * @param sala_puerta Identificador de la sala con la puerta de laberinto
	 * @param dim_x El ancho con el que configurar del laberinto
	 * @param dim_y El alto con el que configurar del laberinto
	 * Complejidad: O(n)
	 */
	public void configurarLaberinto(int sala_puerta, int dim_x, int dim_y, 
			int altura_arbol){
		ancho = dim_x;
		alto = dim_y;
		this.altura_arbol = altura_arbol;
		this.sala_puerta = sala_puerta;
		for(int i=0; i<ancho*alto; i++){
			salas.put(i, new Sala(i));
		}
		salas.put(sala_ganadores, new Sala(sala_ganadores));
		max_turnos = 50;
	}
	
	/**
	 * Distribuye un conjunto de llaves a las salas indicadas.
	 * PRE: El laberinto debe estar creado correctamente y los parametros ser correctos
	 * POST: Las llaves indicadas son distribuidas en las salas que se indican
	 * @param id_salas_llaves Array con los identificadores de salas en las que distribuir las llaves
	 * @param llaves_sala Array de llaves a distribuir
	 * Complejidad: O(n^2)
	 */
	public void distribuirLlaves(int[] id_salas_llaves, int[] llaves_sala){
		for(int i = 0;i<id_salas_llaves.length;i++){
			Sala sala = salas.get(id_salas_llaves[i]);
			for(int x = 0; x<5; x++)
				sala.meterLlave(new Llave(llaves_sala[i*5+x]));
		}
	}
	
	/**
	 * Configura la puerta de la sala de salida
	 * PRE: El laberinto debe estar creado correctamente y la combinacion ser válida
	 * POST: se configura la puerta con la combinación dada
	 * @param combinacion Combinación con la que configurar la puerta
	 * Complejidad: O(n)
	 */
	public void configurarPuerta(int[] combinacion){
		Sala sala = salas.get(sala_puerta);
		sala.configurarPuerta(combinacion, altura_arbol);
	}

	/**
	 * Inserta el robot dado en su sala correspondiente.
	 * PRE: El laberinto debe estar creado correctamente y el robot ser correcto
	 * POST: Mete el robot en el laberinto
	 * @param robot
	 * Complejidad: O(1)
	 */
	public void meterRobot(Robot robot){
		Sala sala = salas.get(robot.obtenerSala());
		sala.meterRobot(robot);
	}
	
	/**
	 * Mueve un robot de la sala antigua a la nueva.
	 * PRE: El laberinto debe estar creado correctamente y los identificadores de las salas ser correctos
	 * POST: Mueve el robot en el laberinto, de la sala antigua a la nueva
	 * @param id_sala_antigua
	 * @param id_sala_nueva
	 * Complejidad: O(1)
	 */
	public void moverRobot(int id_sala_antigua, int id_sala_nueva){
		//TODO cambiar esto, implementar un metodo en laberinto que obtenga una
		//sala y asi meter el robot
		Sala sala_antigua = salas.get(id_sala_antigua);
		Robot robot = sala_antigua.sacarRobot();
		Sala sala_nueva = salas.get(id_sala_nueva);
		sala_nueva.meterRobot(robot);
	}
	
	/**
	 * Realiza la simulación completa del laberinto.
	 * PRE: El laberinto debe estar creado correctamente
	 * POST: Realiza la simulación del laberinto
	 * Complejidad: O(n^2)
	 */
	public void simular(){
		pintarLaberinto();
		Sala s = salas.get(sala_puerta);
		Puerta p = s.obtenerPuerta();
		turno_actual = 0;
		while(turno_actual<max_turnos && p.estadoPuerta() == Estados.Cerrada){
			simularTurno(turno_actual);
			turno_actual++;
		}
		if(p.estadoPuerta() == Estados.Abierta)
			System.out.println(salas.get(sala_ganadores).stringGanadores());
	}
	
	/**
	 * Módulo privado para simular un turno completo en el laberinto
	 * PRE: El laberinto debe estar creado correctamente
	 * POST: Realiza la simulación de un turno en el laberinto
	 * Complejidad: O(n)
	 * @param turno
	 */
	private void simularTurno(int turno){
		for(Entry<Integer, Sala> par_sala : salas.entrySet()){
			par_sala.getValue().simularTurno(turno);
		}
		pintarLaberinto();
	}
	
	/**
	 * Pinta un laberinto 2D y muestra el estado de este.
	 * PRE: El laberinto debe ser correcto
	 * POST: Pinta el estado actual del laberinto.
	 * Complejidad: O(n^2)
	 */
	public void pintarLaberinto(){
		System.out.println(toString());
		for(int i = 0;i<ancho;i++)
			System.out.print(" _");
		System.out.println("");
		for(int i = 0;i<alto;i++){
			System.out.print("|");
			for(int j = 0;j<ancho;j++){
				Sala s = salas.get(ancho*i+j);
				System.out.print(s.robotsEnSala() + "|");
			}
			System.out.println("");
		}
	}

	/**
	 * Comprueba si una sala dada tiene puerta.
	 * PRE: El laberinto debe estar creado correctamente y el identificador de la sala a consultar ser correcto
	 * POST: Devuelve si la sala indicada tiene puerta o no tiene
	 * @param id_sala
	 * @return boolean True si tiene puerta y false en caso contrario
	 * Complejidad: O(n)
	 */
	public boolean tienePuerta(int id_sala) {
		Sala s = salas.get(id_sala);
		return (s.obtenerPuerta() == null) ? false : true;
	}

	/**
	 * Obtiene la puerta de una sala dada si la tiene
	 * PRE: El laberinto debe estar creado correctamente y el identificador de la sala a consultar ser correcto
	 * POST: Si la sala indicada tiene puerta la devuelve
	 * @param id_sala
	 * @return Puerta
	 * Complejidad: O(n)
	 */
	public Puerta obtenerPuerta(int id_sala) {
		Sala s = salas.get(id_sala);
		return s.obtenerPuerta();
	}

	/**
	 * Obtiene una sala dado su identificador.
	 * PRE: El laberinto debe estar creado correctamente y el identificador de la sala a consultar ser correcto
	 * POST: Devuelve la sala con el identificador dado
	 * @param id_sala
	 * @return Sala
	 * Complejidad: O(n)
	 */
	public Sala obtenerSala(int id_sala) {
		return salas.get(id_sala);
	}
	
	@Override
	public String toString() {
		Sala sala = salas.get(sala_puerta);
		Puerta puerta = sala.obtenerPuerta();
		return "(turno:" + turno_actual + ")\n" + "(laberinto:" + sala_puerta + 
				")\n" + puerta.toString();
	}

	public boolean puertaAbierta() {
		Sala s = salas.get(sala_puerta);
		Puerta p = s.obtenerPuerta();
		return (p.estadoPuerta() == Estados.Abierta);
	}
}