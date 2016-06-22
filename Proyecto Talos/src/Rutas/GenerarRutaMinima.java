package Rutas;

import java.util.ArrayDeque;
import java.util.Deque;

import Robots.Direccion;
import Talos.Laberinto;

/**
 * Clase GenerarRutaMinima, genera rutas mediante el uso de grafos y el 
 * algoritmo de camino minimo.
 * @version 0.1 11/12/2014
 * @author Grupo Talos { Jorge Bote Albalá, Juan Jose Ramón Rodríguez }
 */
public class GenerarRutaMinima implements GeneradorRutas{

	@Override
	public Deque<Direccion> generarRuta() {
		Deque<Direccion> ruta = new ArrayDeque<Direccion>();
		Laberinto laberinto = Laberinto.getInstancia();
		int SE = (laberinto.getAlto()*laberinto.getAncho())-1;
		int NE = laberinto.getAncho()-1;
		int NO = 0;
		int SO = laberinto.getAncho()*(laberinto.getAlto()-1);
		ruta.addAll(camino(SE, NE));
		ruta.addAll(camino(NE, NO));
		ruta.addAll(camino(NO, SO));
		ruta.addAll(camino(SO, SE));
		return ruta;
	}
	
	/**
	 * Metodo que calcula la ruta minima desde un origen a un destino.
	 * @param origen, sala de origen de la ruta
	 * @param destino, sala de destino de la ruta
	 * @return Deque<Direccion> retorna la secuencia de direcciones.
	 * Complejidad: O(n)
	 */
	private Deque<Direccion> camino(int origen, int destino){
		Deque<Direccion> camino = new ArrayDeque<Direccion>();
		Laberinto laberinto = Laberinto.getInstancia();
		while(origen != destino){
			int siguiente = laberinto.obtenerSiguiente(origen, destino);
			if(origen == siguiente+laberinto.getAncho())
				camino.add(Direccion.N);
			else if(origen == siguiente-laberinto.getAncho())
				camino.add(Direccion.S);
			else if(origen == siguiente+1)
				camino.add(Direccion.O);
			else if(origen == siguiente-1)
				camino.add(Direccion.E);
			origen = siguiente;
		}
		return camino;
	}
}
