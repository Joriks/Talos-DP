package Robots;

import Excepciones.RobotException;
import Rutas.GenerarRutaProfunda;

/**
 * Clase Bender, hereda de la clase robot.
 * @version 0.2 30/10/2014
 * @author Grupo Talos { Jorge Bote Albalá, Juan Jose Ramón Rodríguez }
 */
public class Bender extends Robot{
	
	public Bender() throws RobotException {
		super("bender",'\0', 0, 0);
		generar_ruta = new GenerarRutaProfunda();
		ruta = generar_ruta.generarRuta();
	}
	
	public Bender(String nombre, char marca, int turno, int sala_actual) throws RobotException{
		super(nombre, marca, turno, sala_actual);
		generar_ruta = new GenerarRutaProfunda();
		ruta = generar_ruta.generarRuta();
	}
}
