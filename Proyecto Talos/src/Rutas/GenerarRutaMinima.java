package Rutas;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

import Robots.Direccion;

/**
 * Clase GenerarRutaMinima, genera rutas mediante el uso de grafos y el 
 * algoritmo de camino minimo.
 * @version 0.1 11/12/2014
 * @author Grupo Talos { Jorge Bote Albalá, Juan Jose Ramón Rodríguez }
 */
public class GenerarRutaMinima implements GeneradorRutas{

	@Override
	public Deque<Direccion> generarRuta() {
		//TODO Implementar algoritmo
		Direccion[] direcciones = {Direccion.N, Direccion.N, Direccion.O,
				Direccion.N, Direccion.N, Direccion.O, Direccion.S,
				Direccion.O, Direccion.O, Direccion.N, Direccion.N,
				Direccion.O, Direccion.S, Direccion.S, Direccion.S,
				Direccion.S, Direccion.S, Direccion.E, Direccion.E,
				Direccion.E, Direccion.E, Direccion.E};
		Deque<Direccion> ruta = new ArrayDeque<Direccion>();
		ruta.addAll(Arrays.asList(direcciones));
		return ruta;
	}

}
