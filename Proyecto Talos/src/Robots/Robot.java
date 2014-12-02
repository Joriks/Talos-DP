package Robots;

import java.util.ArrayDeque;
import java.util.Deque;

import Excepciones.RobotException;

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
			throw new RobotException("Configuracion");
		this.turno = turno;
		if(sala_actual < 0)
			throw new RobotException("Configuracion");
		this.sala_actual = sala_actual;
		llaves = new ArrayDeque<Llave>();
		ruta = new ArrayDeque<Direccion>();
	}
	
	/**
	 * Asigna el conjunto de direcciones a la ruta del robot.
	 * @param direcciones
	 */
	public void asignarRuta(Direccion[] direcciones){
		for (Direccion movimiento : direcciones)
			ruta.addLast(movimiento);
//		ruta.addAll(Arrays.asList(direcciones));//se puede cambiar por esto
	}

	/**
	 * Obtiene el identificador de la sala en la que se encuentra el robot
	 * en ese turno.
	 * @return
	 */
	public int obtenerSala(){
		return sala_actual;
	}
	
	/**
	 * Obtiene la marca identificativa del robot.
	 * @return
	 */
	public char obtenerMarca(){
		return marca;
	}
	
	/**
	 * Simula la ejecución de un turno del robot
	 * PRE:
	 * POST:
	 * Complejidad: O(log n)
	 * @param laberinto 
	 */
	public void simularTurno(int turno_actual){
		if(this.turno == turno_actual){
			interactuarPuerta();
			if(puedeMover())
				moverRobot();
			interactuarLlave();
			incrementarTurno();
		}
		
		
		
		//TODO CAMBIAR por un hook y ese hook implementarlo en las clases heredadas
//		if(this.turno == turno_actual){
//			Laberinto laberinto = Laberinto.getInstancia();
//			if(laberinto.tienePuerta(sala_actual)){//TODO esto modificarlo, 
				//para que sea la sala la que obtiene una llave y la prueba de llave
				//en su puerta "si tiene" no como ahora.
//				Puerta p = laberinto.obtenerPuerta(sala_actual);
//				interactuarPuerta(p);
//				if(p.estadoPuerta() == Estados.Abierta){
//					laberinto.moverRobot(sala_actual, Laberinto.sala_ganadores);//TODO El robot se debe mover el
					//no que lo mueva el laberinto
//					sala_actual = Laberinto.sala_ganadores;//cambiar a sala_ganadores
//				}
//			}
//			else
//				moverRobot();
//			interactuarLlave();
//			incrementarTurno();
//			llaves.isEmpty();
//		}
		
		//meter template method con simular y los interactuar
		
		/*El Simular turno debe quedar así
		 * if(turno actual){
		 *	 	interactuarPuerta();
		 * 		moverRobot();
		 * 		interactuarLlave()
		 * 		incrementarTurno()
		 * }
		 */
	}

	/**
	 * Accion a ejecutar sobre la puerta.
	 * @param puerta, puerta sobre la que se va a ejecutar una acción
	 * PRE:
	 * POST:
	 * Complejidad: O(log n)
	 */
	protected void interactuarPuerta(){
		Laberinto laberinto = Laberinto.getInstancia();
		Puerta puerta = laberinto.obtenerPuerta(sala_actual);
		if(puerta != null)
			try {//TODO cambiar por una compbrobación de si hay llaves, mejor en 
				System.out.println("Probada llave " + llaves.peekFirst().toString());
				puerta.probarLlave(llaves.pop());
			} catch (Exception e) {
			}
	}

	protected boolean puedeMover() {
		Laberinto laberinto = Laberinto.getInstancia();
		if(laberinto.tienePuerta(sala_actual))
			return false;
		return true;
	}
	
	/**
	 * Mueve el robot a la sala designada por el movimiento obtenido desde la ruta
	 * PRE:
	 * POST:
	 * Complejidad: O(1)
	 */
	protected void moverRobot(){
		Direccion movimiento = ruta.pollFirst();
		Laberinto laberinto = Laberinto.getInstancia();
		int ancho = laberinto.getAncho(), alto = laberinto.getAlto();
		int sala_antigua = sala_actual;
		
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
			System.err.println("Error de direccion");//Cambiar por DireccionException?
			break;
		}
		laberinto.moverRobot(sala_antigua, sala_actual);
		ruta.addLast(movimiento);
	}
	
	/**
	 * Ejecuta una accion de una llave sobre una sala
	 * @param sala, sala sobre la que se va a realizar la acción de la llave.
	 * PRE:
	 * POST:
	 * Complejidad: O(1).
	 */
	protected void interactuarLlave(){
		Llave llave;
		Laberinto laberinto = Laberinto.getInstancia();
		Sala sala = laberinto.obtenerSala(sala_actual);
		if(sala != null && (llave = sala.sacarLlave()) != null)
			llaves.push(llave);
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
	
	@Override
	public String toString() {
		return "(" + nombre + ":" + marca + ":" + sala_actual + ":" + turno +
				 ":" + llaves.toString().replace(", ", " ").replace("[", "")
				 .replace("]", "") + ")";
	}
}