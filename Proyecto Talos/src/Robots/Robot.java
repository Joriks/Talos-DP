package Robots;

import java.util.LinkedList;
import java.util.TreeSet;

import Talos.Llave;
import Talos.Puerta;
import Talos.Sala;

/**
 * Clase Robot, guarda información sobre las llaves, ruta y sala del robot.
 * @version 0.2 30/10/2014
 * @author Grupo Talos { Jorge Bote Albalá, Juan Jose Ramón Rodríguez }
 */
public abstract class Robot {
	protected String nombre;
	protected char marca;
	protected int turno;
	protected int sala_actual;
	protected TreeSet<Llave> llaves;
	protected LinkedList<Direccion> ruta;
	
	public Robot() {
		nombre = "Robot";
		marca = 'R';
		turno = 0;
		sala_actual = 0;
		llaves = new TreeSet<Llave>();
		ruta = new LinkedList<Direccion>();
		generarRuta();
	}
	
	public Robot(String nombre, char marca, int turno, int sala_actual) {
		this.nombre = nombre;
		this.marca = marca;
		this.turno = turno;
		this.sala_actual = sala_actual;
		llaves = new TreeSet<Llave>();
		ruta = new LinkedList<Direccion>();
		generarRuta();
	}

	/**
	 * Genera la ruta que va a seguir el robot.
	 * PRE:
	 * POST:
	 * Complejidad: O(1).
	 */
	private void generarRuta() {
		ruta.add(Direccion.S);
		ruta.add(Direccion.E);
		ruta.add(Direccion.N);
		ruta.add(Direccion.E);
		ruta.add(Direccion.O);
	}

	/**
	 * Simula la ejecución de un turno del robot
	 * PRE:
	 * POST:
	 * Complejidad: O(log n)
	 */
	public void simularTurno(Sala sala, int turno_actual){
		if(this.turno == turno_actual){
			interactuarPuerta(sala.puerta);
			moverRobot();
			interactuarLlave(sala);
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
			puerta.probarLlave(llaves.pollLast());
	}

	/**
	 * Mueve el robot a la sala designada por el movimiento obtenido desde la ruta
	 * PRE:
	 * POST:
	 * Complejidad: O(1)
	 */
	protected void moverRobot(){
		Direccion movimiento = ruta.poll();
		int ancho = 6, alto = 6;
		switch (movimiento) {//comprobar Este y oeste
		case N:
			if(sala_actual-ancho > 0)
				sala_actual -= ancho;
			break;
		case S:
			if(sala_actual+ancho < ancho*alto)
				sala_actual += ancho;
			break;
		case E:
			if(sala_actual+1 < sala_actual%ancho)
				sala_actual++;
			break;
		case O:
			if(sala_actual-1 > sala_actual%ancho)
				sala_actual--;
			break;

		default:
			System.out.println("Error de direccion");//Cambiar por DireccionException?
			break;
		}
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
		Llave l;
		if(sala != null && (l = sala.sacarLlave()) != null)
			llaves.add(l);
	}
	
	/**
	 * Incrementa el turno del robot.
	 * PRE:
	 * POST:
	 * Complejidad: O(1).
	 */
	private void incrementarTurno() {
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
		sala.puerta = new Puerta(llaves, 5);
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
		s.simularTurno(sala,0);
		s.simularTurno(sala,1);
		s.simularTurno(sala,2);
//		a.simularTurno();
		System.out.println(s.toString());
//		System.out.println(a.toString());
	}
	
}
