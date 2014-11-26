package Robots;

import Excepciones.RobotException;

/**
 * Clase Bender, hereda de la clase robot.
 * @version 0.2 30/10/2014
 * @author Grupo Talos { Jorge Bote Albal�, Juan Jose Ram�n Rodr�guez }
 */
public class Bender extends Robot{
	
	public Bender() throws RobotException {
		super("bender",'\0', 0, 0);
	}
	
	public Bender(String nombre, char marca, int turno, int sala_actual) throws RobotException{
		super(nombre, marca, turno, sala_actual);
	}
}
