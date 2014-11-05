package Robots;

import java.util.ArrayDeque;
import java.util.Deque;

import Talos.Estados;
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
	
	public Robot(String nombre, char marca, int turno, int sala_actual) {
		this.nombre = nombre;
		this.marca = marca;
		this.turno = turno;
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
	public void simularTurno(Laberinto laberinto, int turno_actual){
		if(this.turno == turno_actual){
			if(laberinto.tienePuerta(sala_actual)){
				Puerta p = laberinto.obtenerPuerta(sala_actual);
				interactuarPuerta(p);
				if(p.estadoPuerta() == Estados.Abierta){
					laberinto.moverRobot(sala_actual, 1111);
					sala_actual = 1111;
				}
			}
			else
				moverRobot(laberinto);
			interactuarLlave(laberinto.obtenerSala(sala_actual));
			incrementarTurno();
		}
	}

	/**
	 * Accion a ejecutar sobre la puerta.
	 * @param puerta, puerta sobre la que se va a ejecutar una acción
	 * PRE:
	 * POST:
	 * Complejidad: O(log n)
	 */
	protected void interactuarPuerta(Puerta puerta){
		if(puerta != null)
			try {
				System.out.println("Probada llave" + llaves.peekLast().toString());
				puerta.probarLlave(llaves.pop());
			} catch (Exception e) {
				// TODO: handle exception
			}
	}

	/**
	 * Mueve el robot a la sala designada por el movimiento obtenido desde la ruta
	 * PRE:
	 * POST:
	 * Complejidad: O(1)
	 */
	protected void moverRobot(Laberinto laberinto){
		Direccion movimiento = ruta.pollFirst();
		int ancho = 6, alto = 6;
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
	protected void interactuarLlave(Sala sala){
		Llave llave;
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
				 ":" + llaves.toString() + ")";
	}
	
	public static void main(String[] args) {
		int ancho = 6, alto = 6;
//		Bender b = new Bender('B',0,0);
//		Sonny sy = new Sonny('S',0,0);
		Spirit s = new Spirit('T',0,ancho*(alto-1));
//		Asimo a = new Asimo('A',0,ancho*alto-1);
		Sala sala = new Sala(5);
		int[] llaves = {1,3,5,4};
		sala.configurarPuerta(llaves,5);
		sala.meterLlave(new Llave(5));
		sala.meterLlave(new Llave(1));
		sala.meterLlave(new Llave(3));
		sala.meterLlave(new Llave(8));
		s.llaves.add(new Llave(5));
		s.llaves.add(new Llave(3));
		s.llaves.add(new Llave(4));
		s.llaves.add(new Llave(1));
//		a.llaves.add(new Llave(1));
//		a.llaves.add(new Llave(3));
//		System.out.println(b.toString());
//		System.out.println(sy.toString());
		System.out.println(s.toString());
//		System.out.println(a.toString());
//		b.simularTurno();
//		sy.simularTurno();
//		s.simularTurno(sala,0);
//		s.simularTurno(sala,1);
//		s.simularTurno(sala,2);
//		a.simularTurno();
		System.out.println(s.toString());
//		System.out.println(a.toString());
	}
	
}