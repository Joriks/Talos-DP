package Robots;

import Excepciones.RobotException;
import Rutas.GenerarRutaDerecha;

/**
 * Clase Sonny, hereda de la clase robot.
 * @version 0.2 30/10/2014
 * @author Grupo Talos { Jorge Bote Albal�, Juan Jose Ram�n Rodr�guez }
 */
public class Sonny extends Robot{

	public Sonny() throws RobotException {
		super("sonny",'\0', 0, 0);
		generar_ruta = new GenerarRutaDerecha();
		ruta = generar_ruta.generarRuta();
	}
	
	public Sonny(String nombre, char marca, int turno, int sala_actual) throws RobotException {
		super(nombre, marca, turno, sala_actual);
		generar_ruta = new GenerarRutaDerecha();
		ruta = generar_ruta.generarRuta();
	}
}

