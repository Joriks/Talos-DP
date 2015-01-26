package Robots;

import Excepciones.RobotException;
import Rutas.GenerarRutaMinima;
import Talos.Laberinto;
import Talos.Llave;
import Talos.Puerta;
import Talos.Sala;

/**
 * Clase Asimo, hereda de la clase robot.
 * @version 0.2 30/10/2014
 * @author Grupo Talos { Jorge Bote Albalá, Juan Jose Ramón Rodríguez }
 */
public class Asimo extends Robot{

	public Asimo() throws RobotException {
		super("asimo",'\0', 0, 0);
		int[] comb = {1,3,5,7,9,11,13,15,17,19,21,23,25,27,29};
		for(int id_llave : comb)
			llaves.add(new Llave(id_llave));
		generar_ruta = new GenerarRutaMinima();
		ruta = generar_ruta.generarRuta();
	}
	
	public Asimo(String nombre, char marca, int turno, int sala_actual) throws RobotException{
		super(nombre, marca, turno, sala_actual);
		int[] comb = {1,3,5,7,9,11,13,15,17,19,21,23,25,27,29};
		for(int id_llave : comb)
			llaves.add(new Llave(id_llave));
		generar_ruta = new GenerarRutaMinima();
		ruta = generar_ruta.generarRuta();
	}
	
	@Override
	protected boolean puedeMover() {
		return true;
	}
	
	/**
	 * Accion a ejecutar sobre la puerta.
	 * @param puerta, puerta sobre la que se va a ejecutar una acción
	 * PRE:
	 * POST:
	 * Complejidad: O(log n)
	 */
	@Override
	protected void interactuarPuerta() {
		Laberinto laberinto = Laberinto.getInstancia();
		if(laberinto.tienePuerta(sala_actual)){
			Puerta puerta = laberinto.obtenerPuerta(sala_actual);
			System.out.println(nombre + ":" + marca + ":" + "Cierro puerta");
			puerta.cerrarPuerta();
		}
	}

	/**
	 * Ejecuta una accion de una llave sobre una sala
	 * @param sala, sala sobre la que se va a realizar la acción de la llave.
	 * PRE:
	 * POST:
	 * Complejidad: O(1).
	 */
	@Override
	protected void interactuarLlave() {
		if(sala_actual%2==0){
			Laberinto laberinto = Laberinto.getInstancia();
			Sala sala = laberinto.obtenerSala(sala_actual);
			if(!llaves.isEmpty())
				sala.meterLlave(llaves.pop());
		}
	}
}