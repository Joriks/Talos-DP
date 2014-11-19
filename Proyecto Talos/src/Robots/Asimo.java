package Robots;

import Talos.Laberinto;
import Talos.Llave;
import Talos.Puerta;
import Talos.Sala;

/**
 * Clase Asimo, hereda de la clase robot.
 * @version 0.2 30/10/2014
 * @author Grupo Talos { Jorge Bote Albal�, Juan Jose Ram�n Rodr�guez }
 */
public class Asimo extends Robot{

	public Asimo() {
		super("asimo",'\0', 0, 0);
		int[] comb = {1,3,5,7,9,11,13,15,17,19,21,23,25,27,29};
		for(int id_llave : comb)
			llaves.add(new Llave(id_llave));
	}
	
	public Asimo(String nombre, char marca, int turno, int sala_actual){
		super(nombre, marca, turno, sala_actual);
		int[] comb = {1,3,5,7,9,11,13,15,17,19,21,23,25,27,29};
		for(int id_llave : comb)
			llaves.add(new Llave(id_llave));
	}
	
	/**
	 * Simula la ejecuci�n de un turno del robot
	 * PRE:
	 * POST:
	 * Complejidad: O(log n)
	 * @param laberinto 
	 */
	public void simularTurno(Laberinto laberinto, int turno_actual){
		if(this.turno == turno_actual){
			if(laberinto.tienePuerta(sala_actual))
				interactuarPuerta(laberinto.obtenerPuerta(sala_actual));
			moverRobot(laberinto);
			interactuarLlave(laberinto.obtenerSala(sala_actual));
			incrementarTurno();
		}
	}
	
	/**
	 * Accion a ejecutar sobre la puerta.
	 * @param puerta, puerta sobre la que se va a ejecutar una acci�n
	 * PRE:
	 * POST:
	 * Complejidad: O(log n)
	 */
	@Override
	protected void interactuarPuerta(Puerta puerta) {
		System.out.println(nombre + ":" + marca + ":" + "Cierro puerta");
		puerta.cerrarPuerta();
	}

	/**
	 * Ejecuta una accion de una llave sobre una sala
	 * @param sala, sala sobre la que se va a realizar la acci�n de la llave.
	 * PRE:
	 * POST:
	 * Complejidad: O(1).
	 */
	@Override
	protected void interactuarLlave(Sala sala) {
		try {
			if(sala_actual%2==0)
				sala.meterLlave(llaves.pop());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}