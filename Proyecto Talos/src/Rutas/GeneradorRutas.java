package Rutas;

import java.util.Deque;

import Robots.Direccion;

/**
 * Interface GeneradorRutas, genera rutas para los distintos tipos de robots.
 * @version 0.1 11/12/2014
 * @author Grupo Talos { Jorge Bote Albalá, Juan Jose Ramón Rodríguez }
 */
public interface GeneradorRutas {
	
	/**
	 * Genera una ruta específica y la devuelve.
	 * PRE:
	 * POST:
	 * Complejidad:
	 * @return
	 */
	public Deque<Direccion> generarRuta();
}
