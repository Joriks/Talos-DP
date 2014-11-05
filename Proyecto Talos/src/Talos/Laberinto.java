package Talos;

import java.util.HashMap;
import java.util.Map.Entry;

import Robots.Direccion;
import Robots.Robot;
import Robots.Asimo;
import Robots.Bender;
import Robots.Sonny;
import Robots.Spirit;

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
	
	public static void main(String[] args) {
		
		int dimX = 6, dimY = 6;
		int sala_puerta = (dimX*dimY)-1;
		int altura_arbol = 5;
		int[] id_salas_llaves = {3,4,6,8,9,10,11,12,13};
		int[] llaves_sala = {0,1,1,2,3,3,4,5,5,6,7,7,8,9,9,10,11,11,12,13,13,14
				,15,15,16,17,17,18,19,19,20,21,21,22,23,23,24,25,25,26,27,27,28
				,29,29};
		int[] combinacion = {1,3,5,7,9,11,13,15,17,19,21,23,25,27,29};
		
		Laberinto l = new Laberinto(sala_puerta, dimX, dimY, altura_arbol);
		
		Robot sonny = new Sonny('S',0,0);
		Robot asimo = new Asimo('A',0,35);
		Robot bender = new Bender('B',0,0);
		Robot spirit = new Spirit('T',0,30);
		Robot sonnyn = new Sonny('N',0,0);
		
		Direccion[] ruta_bender = {Direccion.S, Direccion.S, Direccion.E, Direccion.E, Direccion.N, Direccion.E, Direccion.N, Direccion.E, Direccion.S,
				 Direccion.E, Direccion.S, Direccion.S, Direccion.O, Direccion.S, Direccion.E, Direccion.E};
		Direccion[] ruta_sonny = {Direccion.E, Direccion.S, Direccion.S, Direccion.S, Direccion.O, Direccion.S, Direccion.E, Direccion.E, Direccion.N,
				 Direccion.E, Direccion.S, Direccion.S, Direccion.E, Direccion.E};
		Direccion[] ruta_asimo = {Direccion.N, Direccion.N, Direccion.O, Direccion.N, Direccion.N, Direccion.O, Direccion.S, Direccion.O,
				 Direccion.O, Direccion.N, Direccion.N, Direccion.O, Direccion.S, Direccion.S, Direccion.S, Direccion.S, Direccion.S,
				 Direccion.E, Direccion.E, Direccion.E, Direccion.E, Direccion.E};
		Direccion[] ruta_spirit = {Direccion.N, Direccion.N, Direccion.N, Direccion.E, Direccion.S, Direccion.E, Direccion.N, Direccion.N,
				 Direccion.E, Direccion.N, Direccion.E, Direccion.E, Direccion.S, Direccion.S, Direccion.S, Direccion.S, Direccion.S};
		
		sonny.asignarRuta(ruta_sonny);
		asimo.asignarRuta(ruta_asimo);
		bender.asignarRuta(ruta_bender);
		spirit.asignarRuta(ruta_spirit);
		sonnyn.asignarRuta(ruta_sonny);
		
		l.meterRobot(sonny);
		l.meterRobot(asimo);
		l.meterRobot(bender);
		l.meterRobot(spirit);
		l.meterRobot(sonnyn);
		
		l.distribuirLlaves(id_salas_llaves, llaves_sala);
		l.configurarPuerta(combinacion);
		l.simular();
	}
}