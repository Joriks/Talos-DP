package Rutas;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

import Robots.Direccion;

/**
 * Clase GenerarRutaDerecha, genera rutas mediante el algoritmo de la mano
 * derecha.
 * @version 0.1 11/12/2014
 * @author Grupo Talos { Jorge Bote Albalá, Juan Jose Ramón Rodríguez }
 */
public class GenerarRutaDerecha implements GeneradorRutas {

	@Override
	public Deque<Direccion> generarRuta() {
		//TODO Implementar algoritmo
		Direccion[] direcciones = {Direccion.E, Direccion.S, Direccion.S,
				Direccion.S, Direccion.O, Direccion.S, Direccion.E,
				Direccion.E, Direccion.N, Direccion.E, Direccion.S,
				Direccion.S, Direccion.E, Direccion.E};
		Deque<Direccion> ruta = new ArrayDeque<Direccion>();
		ruta.addAll(Arrays.asList(direcciones));
		return ruta;
	}

}
