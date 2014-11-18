package Robots;

/**
 * Clase Bender, hereda de la clase robot.
 * @version 0.2 30/10/2014
 * @author Grupo Talos { Jorge Bote Albalá, Juan Jose Ramón Rodríguez }
 */
public class Bender extends Robot{
	
	public Bender() {
		super("bender",'\0', 0, 0);
	}
	
	public Bender(String nombre, char marca, int turno, int sala_actual){
		super(nombre, marca, turno, sala_actual);
	}
}
