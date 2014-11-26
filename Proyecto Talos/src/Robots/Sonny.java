package Robots;

import Excepciones.RobotException;

/**
 * Clase Sonny, hereda de la clase robot.
 * @version 0.2 30/10/2014
 * @author Grupo Talos { Jorge Bote Albalá, Juan Jose Ramón Rodríguez }
 */
public class Sonny extends Robot{

	public Sonny() throws RobotException {
		super("sonny",'\0', 0, 0);
	}
	
	public Sonny(String nombre, char marca, int turno, int sala_actual) throws RobotException {
		super(nombre, marca, turno, sala_actual);
	}
}

