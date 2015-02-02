package Robots;

import Excepciones.RobotException;
import Rutas.GenerarRutaMinimaCentral;

public class AsimoOeste extends Asimo{

	public AsimoOeste() throws RobotException {
		super();
	}
	
	public AsimoOeste(String nombre, char marca, int turno, int sala_actual) throws RobotException{
		super(nombre, marca, turno, sala_actual);
		generar_ruta = new GenerarRutaMinimaCentral();
		ruta = generar_ruta.generarRuta();
	}

}
