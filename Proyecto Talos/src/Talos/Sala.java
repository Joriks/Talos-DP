package Talos;

import java.util.LinkedList;
import java.util.TreeSet;

import Robots.Robot;

/**
 * Clase Puerta, guarda información sobre la cerradura de salida del laberinto.
 * @version 0.2 06/10/2014
 * @author Grupo Talos { Jorge Bote Albalá, Juan Jose Ramón Rodríguez }
 */
public class Sala {
	private int id_sala;
	private TreeSet<Llave> llaves_sala;
	private LinkedList<Robot> robot_sala;
	
	public Sala(int id){
		id_sala = id;
		llaves_sala = new TreeSet<Llave>();
		robot_sala = new LinkedList<Robot>();
	}
	
	public Sala(int id, TreeSet<Llave> llaves_sala){
		id_sala = id;
		this.llaves_sala = llaves_sala;
		robot_sala = new LinkedList<Robot>();
	}
	
	//Como se podría hacer con la llave como parámetro
	public Llave sacarLlave(){
		return llaves_sala.pollFirst();
	}
	
	public void meterLlave(Llave llave){
		llaves_sala.add(llave);
	}
	
	public Robot sacarRobot(){
		return robot_sala.removeFirst();
	}
	
	public void meterRobot(){
		robot_sala.pollLast();
	}
	
	public void simularTurno(){
		//TODO
	}
	
	@Override
	public String toString() {
		return "(sala:" + id_sala + ":" + llaves_sala.toString() + ")";
	}


	public static void main(String[] args) {
		Sala s= new Sala(1);
		s.meterLlave(new Llave(1));
		s.meterLlave(new Llave(2));
		s.meterLlave(new Llave(3));
		Llave l=null, l2=null;
		l=s.sacarLlave();
		System.out.println(l.toString());
		l2=s.sacarLlave();
		System.out.println(l2.toString());
		l2=s.sacarLlave();
		System.out.println(l2.toString());
		l2=s.sacarLlave();
		if(l2!=null)
			System.out.println(l2.toString());
		else
			System.out.println("No hay llave");
	}
}
