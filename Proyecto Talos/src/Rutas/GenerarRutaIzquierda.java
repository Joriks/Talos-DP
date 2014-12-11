package Rutas;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

import Robots.Direccion;

/**
 * Clase GenerarRutaIzquierda, genera rutas mediante el algoritmo de la mano
 * izquierda.
 * @version 0.1 11/12/2014
 * @author Grupo Talos { Jorge Bote Albalá, Juan Jose Ramón Rodríguez }
 */
public class GenerarRutaIzquierda implements GeneradorRutas {

	@Override
	public Deque<Direccion> generarRuta() {
		//TODO Implementar algoritmo
		Direccion[] direcciones = {Direccion.N, Direccion.N, Direccion.N,
				Direccion.E, Direccion.S, Direccion.E, Direccion.N,
				Direccion.N, Direccion.E, Direccion.N, Direccion.E,
				Direccion.E, Direccion.S, Direccion.S, Direccion.S,
				Direccion.S, Direccion.S};
		Deque<Direccion> ruta = new ArrayDeque<Direccion>();
		ruta.addAll(Arrays.asList(direcciones));
		return ruta;
	}

}
