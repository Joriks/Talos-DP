package Robots;

import Excepciones.RobotException;

/**
 * Clase Spirit, hereda de la clase robot.
 * @version 0.2 30/10/2014
 * @author Grupo Talos { Jorge Bote Albalá, Juan Jose Ramón Rodríguez }
 */
public class Spirit extends Robot{

	public Spirit() throws RobotException {
		super("spirit",'\0', 0, 0);
	}
	
	public Spirit(String nombre, char marca, int turno, int sala_actual) throws RobotException {
		super(nombre, marca, turno, sala_actual);
	}
}

