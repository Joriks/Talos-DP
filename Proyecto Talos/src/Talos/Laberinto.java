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

	private int ancho;
	private int alto;
	private int altura_arbol;
	private int sala_puerta;
	private HashMap<Integer, Sala> salas;
	private int max_turnos;
	private int turno_actual;
	
	public Laberinto(int sala_puerta,int dimX,int dimY,int altura_arbol){
		ancho = dimX;
		alto = dimY;
		this.altura_arbol = altura_arbol;
		this.sala_puerta = sala_puerta;
		salas = new HashMap<Integer, Sala>();
		for(int i=0; i<dimX*dimY; i++){
			salas.put(i, new Sala(i));
		}
		salas.put(1111, new Sala(1111));
		max_turnos = 50;
	}
	
	/**
	 * Distribuye un conjunto de llaves a las salas indicadas.
	 * @param id_salas_llaves
	 * @param llaves_sala
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
	 * @param combinacion
	 */
	public void configurarPuerta(int[] combinacion){
		Sala sala = salas.get(sala_puerta);
		sala.configurarPuerta(combinacion, altura_arbol);
	}

	/**
	 * Inserta el robot dado en su sala correspondiente.
	 * @param robot
	 */
	public void meterRobot(Robot robot){
		Sala sala = salas.get(robot.obtenerSala());
		sala.meterRobot(robot);
	}
	
	/**
	 * Mueve un robot de la sala antigua a la nueva.
	 * @param id_sala_antigua
	 * @param id_sala_nueva
	 */
	public void moverRobot(int id_sala_antigua, int id_sala_nueva){
		Sala sala_antigua = salas.get(id_sala_antigua);
		Robot robot = sala_antigua.sacarRobot();
		Sala sala_nueva = salas.get(id_sala_nueva);
		sala_nueva.meterRobot(robot);
	}
	
	/**
	 * Realiza la simulación completa del laberinto.
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
			System.out.println(salas.get(1111).stringGanadores());
	}
	
	/**
	 * Simula un turno completo en el laberinto
	 * @param turno
	 */
	private void simularTurno(int turno){
		for(Entry<Integer, Sala> par_sala : salas.entrySet()){
			par_sala.getValue().simularTurno(this,turno);
		}
		pintarLaberinto();
	}
	
	/**
	 * Pinta un laberinto 2D y muestra el estado de este.
	 */
	public void pintarLaberinto(){
		System.out.println(toString());
		for(int i = 0;i<ancho;i++)
			System.out.print(" _");
		System.out.println("");
		for(int i = 0;i<alto;i++){
			System.out.print("|");
			for(int j = 0;j<ancho;j++){
				Sala s = salas.get(alto*i+j);
				System.out.print(s.robotsEnSala() + "|");
			}
			System.out.println("");
		}
	}
	
	/**
	 * Comprueba si una sala dada tiene puerta.
	 * @param id_sala
	 * @return
	 */
	public boolean tienePuerta(int id_sala) {
		Sala s = salas.get(id_sala);
		return (s.obtenerPuerta() == null) ? false : true;
	}

	/**
	 * Obtiene la puerta de una sala dada si la tiene
	 * @param id_sala
	 * @return
	 */
	public Puerta obtenerPuerta(int id_sala) {
		Sala s = salas.get(id_sala);
		return s.obtenerPuerta();
	}

	/**
	 * Obtiene una sala dado su identificador.
	 * @param id_sala
	 * @return
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
}