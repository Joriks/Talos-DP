package Talos;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Stack;
import java.util.TreeSet;

import Estructuras.Arbol;
import Estructuras.Lista;
import Estructuras.Cola;

/**
 * Clase Puerta, guarda información sobre la cerradura de salida del laberinto.
 * @version 0.2 06/10/2014
 * @author Grupo Talos { Jorge Bote Albalá, Juan Jose Ramón Rodríguez }
 */
public class Sala {
	private int id_sala;
	private TreeSet<Llave> llaves_sala;
	private LinkedList<Integer> robot_sala;
	
	public Sala(int id){
		id_sala = id;
		llaves_sala = new TreeSet<Llave>();
		robot_sala = new LinkedList<Integer>();
	}
	
	public Sala(int id, TreeSet<Llave> llaves_sala){
		id_sala = id;
		this.llaves_sala = llaves_sala;
		robot_sala = new LinkedList<Integer>();
	}
	
	public void putId(int idSala){
		id_sala=idSala;
	}
	
	public boolean sacarLlave(Llave l){
		if (llaves_sala.isEmpty())
			return false;
		l=llaves_sala.pollFirst();
		return true;
	}
	
	public void meterLlave(Llave llave){
		llaves_sala.add(llave);
	}
	
	public boolean sacarRobot(){
		if (robot_sala.isEmpty())
			return false;
		robot_sala.removeFirst();
		return true;
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
}
