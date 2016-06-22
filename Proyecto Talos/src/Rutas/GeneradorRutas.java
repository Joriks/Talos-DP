package Rutas;

import java.util.Deque;

import Robots.Direccion;

/**
 * Interface GeneradorRutas, genera rutas para los distintos tipos de robots.
 * @version 0.1 11/12/2014
 * @author Grupo Talos { Jorge Bote Albal�, Juan Jose Ram�n Rodr�guez }
 */
public interface GeneradorRutas {
	
	/**
	 * Genera una ruta espec�fica y la devuelve.
	 * PRE:
	 * POST:
	 * Complejidad:
	 * @return
	 */
	public Deque<Direccion> generarRuta();
}
