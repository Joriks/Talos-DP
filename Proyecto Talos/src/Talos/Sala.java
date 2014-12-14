package Talos;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.CopyOnWriteArrayList;

import Robots.Robot;

/**
 * Clase Sala, guarda información sobre una sala del laberinto.
 * @version 0.2 30/10/2014
 * @author Grupo Talos { Jorge Bote Albalá, Juan Jose Ramón Rodríguez }
 */
public class Sala {
	
	/** Guarda el identificador de la sala*/
	private int id_sala;
	
	/** Lista de llaves guardadas en la sala*/
	private LinkedList<Llave> llaves_sala;
	
	/** Cola de robots que se encuentran en la sala*/
	private Deque<Robot> robots_sala;
	
	/** Marca que indica la conexión entre salas*/
	private int marca;

	/** Información de la puerta, si la tiene*/
	private Puerta puerta;
	
	public Sala(int id){
		id_sala = id;
		llaves_sala = new LinkedList<Llave>();
		robots_sala = new ArrayDeque<Robot>();
		puerta = null;
	}
	
	public Sala(int id, LinkedList<Llave> llaves_sala){
		id_sala = id;
		this.llaves_sala = llaves_sala;
		robots_sala = new ArrayDeque<Robot>();
		puerta = null;
	}
	
	public int getMarca() {
		return marca;
	}

	public void setMarca(int marca) {
		this.marca = marca;
	}
	
	public Puerta obtenerPuerta(){
		return puerta;
	}
	
	/**
	 * Metodo para configurar inicialmente la puerta de la sala.
	 * @param combinacion Combinación con la que configurar la puerta
	 * @param altura Altura del árbol con la que configurar la puerta
	 * Complejidad: O(n)
	 */
	public void configurarPuerta(int[] combinacion, int altura){
		puerta = new Puerta(combinacion, altura);
	}
	
	/**
	 * Metodo que saca y devuelve la primera llave a sacar
	 * @return la llave que saca
	 * Complejidad: O(1)
	 */
	public Llave sacarLlave(){	
		return llaves_sala.pollFirst();
	}
	
	/**
	 * Metodo que introduce una llave en la sala 
	 * @param llave Llave a meter en la sala
	 * Complejidad: O(n)
	 */
	public void meterLlave(Llave llave){
		llaves_sala.add(llave);
		Collections.sort(llaves_sala);
	}
	
	/**
	 * Metodo que saca y devuelve el primer robot a salir de la sala
	 * @return Robot El robot que sale
	 * Complejidad: O(1)
	 */
	public Robot sacarRobot(){
		return robots_sala.poll();
	}
	
	/**
	 *  Metodo que introduce un robot en la sala
	 *  Complejidad: O(1)
	 */
	public void meterRobot(Robot robot){
		robots_sala.addLast(robot);
	}
	
	/**
	 * Metodo para actualizar el turno de los robots de la sala.
	 * @param turno_actual Información sobre el turno actual en la simulación
	 * Complejidad: O(n log n)
	 */
	public void simularTurno(int turno_actual){
		//Optimizar con un contador o dejar con el copyonwirte
		CopyOnWriteArrayList<Robot> robots = new CopyOnWriteArrayList<Robot>(robots_sala);
		for(Robot robot : robots)
			robot.simularTurno(turno_actual);
	}
	
	public String robotsEnSala(){
		if(robots_sala.size() == 1){
			char marca = robots_sala.getFirst().obtenerMarca();
			return Character.toString(marca);
		}
		else
			if(robots_sala.size() == 0)
				return "_";
			else
				return Integer.toString(robots_sala.size());
	}
	
	public String stringGanadores(){
		return "(robotsganadores)\n" + robots_sala.toString().replace(", ", "\n")
				.replace("[", "").replace("]", "");
	}

	@Override
	public String toString() {
		return "(sala:" + id_sala + ":" + llaves_sala.toString().replace(", ", "\n")
				.replace("[", "").replace("]", "") + ")";
	}
}
