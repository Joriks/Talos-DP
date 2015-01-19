package Rutas;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

import Robots.Direccion;

/**
 * Clase GenerarRutaProfunda, genera rutas mediante el uso de grafos y el
 * algoritmo de profundidad.
 * @version 0.1 11/12/2014
 * @author Grupo Talos { Jorge Bote Albalá, Juan Jose Ramón Rodríguez }
 */
public class GenerarRutaProfunda implements GeneradorRutas {

	@Override
	public Deque<Direccion> generarRuta() {
		//TODO Implementar algoritmo
		Direccion[] direcciones = {Direccion.S, Direccion.S, Direccion.E, 
				Direccion.E, Direccion.N, Direccion.E, Direccion.N,
				Direccion.E, Direccion.S, Direccion.E, Direccion.S,
				Direccion.S, Direccion.O,Direccion.S, Direccion.E,
				Direccion.E};
		Deque<Direccion> ruta = new ArrayDeque<Direccion>();
		ruta.addAll(Arrays.asList(direcciones));
		return ruta;
		}
	}


