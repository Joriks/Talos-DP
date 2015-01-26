package Robots;

import java.util.ArrayDeque;
import java.util.Deque;

import Excepciones.RobotException;
import Rutas.GeneradorRutas;
import Talos.Laberinto;
import Talos.Llave;
import Talos.Puerta;
import Talos.Sala;

/**
 * Clase Robot, guarda información sobre las llaves, ruta y sala del robot.
 * @version 0.2 30/10/2014
 * @author Grupo Talos { Jorge Bote Albalá, Juan Jose Ramón Rodríguez }
 */
public abstract class Robot {
	
	/** Nombre del robot */
	protected String nombre;
	
	/** Marca unica e identificativa del robot */
	protected char marca;
	
	/** Turno que ha ejecutado por ultima vez */
	protected int turno;
	
	/** Identificador de la sala en la que se encuentra el robot */
	protected int sala_actual;
	
	/** Conjunto de llaves que tiene el robot */
	protected Deque<Llave> llaves;
	
	/** Generador de la ruta para el robot */
	protected GeneradorRutas generar_ruta;
	
	/** Conjunto de movimientos que realiza el robot */
	protected Deque<Direccion> ruta;
		
	public Robot() {
		nombre = "Robot";
		marca = 'R';
		turno = 0;
		sala_actual = 0;
		llaves = new ArrayDeque<Llave>();
		ruta = new ArrayDeque<Direccion>();
	}
	
	public Robot(String nombre, char marca, int turno, int sala_actual) throws RobotException{
		this.nombre = nombre;
		this.marca = marca;
		if(turno < 0)
			throw new RobotException("(Robot:" + nombre + ":" + marca + ":" 
			+ sala_actual + ":" + turno + ")");
		this.turno = turno;
		if(sala_actual < 0)
			throw new RobotException("(Robot:" + nombre + ":" + marca + ":" 
					+ sala_actual + ":" + turno + ")");
		this.sala_actual = sala_actual;
		llaves = new ArrayDeque<Llave>();
		ruta = new ArrayDeque<Direccion>();
	}

	/**
	 * Obtiene el identificador de la sala en la que se encuentra el robot
	 * en ese turno.
	 * @return sala dondese encuentra el robot
	 */
	public int obtenerSala(){
		return sala_actual;
	}
	
	/**
	 * Obtiene la marca identificativa del robot.
	 * @return marca del robot
	 */
	public char obtenerMarca(){
		return marca;
	}
	
	/**
	 * Simula la ejecución de un turno del robot
	 * PRE:
	 * POST:
	 * Complejidad: O(log n)
	 * @param turno_actual, indica el turno por el que va la simulacion.
	 * return true si realiza alguna accion, false en caso contrario.
	 */
	public boolean simularTurno(int turno_actual){
		if(this.turno == turno_actual){
			interactuarPuerta();
			if(puedeMover())
				try {
					moverRobot();
				} catch (RobotException robot_exception) {
					System.err.println(robot_exception.getMovimientoMessage());
				}
			interactuarLlave();
			incrementarTurno();
			return true;
		}
		else
			return false;
	}

	/**
	 * Accion a ejecutar sobre la puerta.
	 * PRE:
	 * POST:
	 * Complejidad: O(log n)
	 */
	protected void interactuarPuerta(){
		Laberinto laberinto = Laberinto.getInstancia();
		if(laberinto.tienePuerta(sala_actual)){
			Puerta puerta = laberinto.obtenerPuerta(sala_actual);
			if(!llaves.isEmpty())
				puerta.probarLlave(llaves.pop());
		}
	}

	/**Hook que comprueba si el robot en cuestion puede moverse
	 * PRE:
	 * POST:
	 * @return true si el robot puede moverse, false en caso contrario.
	 * Complejidad: O(1)
	 */
	protected boolean puedeMover() {
		Laberinto laberinto = Laberinto.getInstancia();
		if(laberinto.tienePuerta(sala_actual)){
			if(laberinto.puertaAbierta())
				return true;
			return false;
		}
		return true;
	}
	
	/**
	 * Mueve el robot a la sala designada por el movimiento obtenido desde la ruta
	 * PRE:
	 * POST:
	 * Complejidad: O(1)
	 */
	protected void moverRobot() throws RobotException{
		Laberinto laberinto = Laberinto.getInstancia();
		int ancho = laberinto.getAncho(), alto = laberinto.getAlto();
		int sala_antigua = sala_actual;
		
		if(laberinto.puertaAbierta())
			sala_actual = Laberinto.sala_ganadores;
		else{
			Direccion movimiento = ruta.pollFirst();
			switch (movimiento) {
			case N:
				if(sala_actual-ancho > 0)
					sala_actual -= ancho;
				break;
			case S:
				if(sala_actual+ancho < ancho*alto)
					sala_actual += ancho;
				break;
			case E:
				if(sala_actual%ancho != ancho-1)
					sala_actual++;
				break;
			case O:
				if(sala_actual%ancho != 0)
					sala_actual--;
				break;

			default:
				throw new RobotException("(Robot:" + nombre + ":" + marca + ":" 
			+ sala_actual + ":" + turno + ")");
			}
			ruta.addLast(movimiento);
		}
		Sala sala = laberinto.getSala(sala_antigua);
		Robot robot = sala.sacarRobot();
		sala = laberinto.getSala(sala_actual);
		sala.meterRobot(robot);
	}
	
	/**
	 * Ejecuta una accion de una llave sobre una sala
	 * @param sala, sala sobre la que se va a realizar la acción de la llave.
	 * PRE:
	 * POST:
	 * Complejidad: O(1).
	 */
	protected void interactuarLlave(){
		Laberinto laberinto = Laberinto.getInstancia();
		Sala sala = laberinto.obtenerSala(sala_actual);
		if(sala.tieneLlaves()){
			llaves.push(sala.sacarLlave());
		}
	}
	
	/**
	 * Incrementa el turno del robot.
	 * PRE:
	 * POST:
	 * Complejidad: O(1).
	 */
	protected void incrementarTurno() {
		turno++;
	}
	
	/**
	 * Muestra la ruta que segurira el robot
	 * PRE:
	 * POST:
	 * Complejidad: O(1)
	 */
	public void mostrarRuta(){
		System.out.println("(ruta:" + marca + ":" + ruta.toString().
				replace(", ", " ").replace("[", "").replace("]", "") + ")");
	}
	
	@Override
	public String toString() {
		return "(" + nombre + ":" + marca + ":" + sala_actual + ":" + turno +
				 ":" + llaves.toString().replace(", ", " ").replace("[", "")
				 .replace("]", "") + ")";
	}
}