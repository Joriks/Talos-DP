package Robots;

import java.util.LinkedList;
import java.util.TreeSet;

import Talos.Llave;
import Talos.Puerta;
import Talos.Sala;

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

	private void generarRuta() {
		ruta.add(Direccion.S);
		ruta.add(Direccion.E);
		ruta.add(Direccion.N);
		ruta.add(Direccion.E);
		ruta.add(Direccion.O);
	}

	public void simularTurno(){
		interactuarPuerta(new Puerta());
		moverRobot();
		interactuarLlave(new Sala());
		incrementarTurno();
	}

	protected void interactuarPuerta(Puerta puerta){
//		puerta.probarLlave(llaves.removeLast());
		System.out.println(nombre + ":" + marca + ":" + "Abro puerta");
	}

	protected void moverRobot(){
		Direccion movimiento = ruta.poll();
		switch (movimiento) {
		case N:
			System.out.println(nombre + ":" + marca + ":" + "Moviendo al norte");
			break;
		case S:
			System.out.println(nombre + ":" + marca + ":" + "Moviendo al sur");			
			break;
		case E:
			System.out.println(nombre + ":" + marca + ":" + "Moviendo al este");
			break;
		case O:
			System.out.println(nombre + ":" + marca + ":" + "Moviendo al oeste");
			break;

		default:
			System.out.println("Error de direccion");
			break;
		}
		ruta.addLast(movimiento);
	}
	
	protected void interactuarLlave(Sala s){
		llaves.add(s.obtenerLlave());
		System.out.println(nombre + ":" + marca + ":" + "Cojo llave");
	}
	
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
		s.llaves.add(new Llave(5));
		s.llaves.add(new Llave(3));
		s.llaves.add(new Llave(1));
//		a.llaves.add(new Llave(1));
//		a.llaves.add(new Llave(3));
//		System.out.println(b.toString());
//		System.out.println(sy.toString());
		System.out.println(s.toString());
//		System.out.println(a.toString());
//		b.simularTurno();
//		sy.simularTurno();
		s.simularTurno();
//		a.simularTurno();
		System.out.println(s.toString());
//		System.out.println(a.toString());
	}
	
}
