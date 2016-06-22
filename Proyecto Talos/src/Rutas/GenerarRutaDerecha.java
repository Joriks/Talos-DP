package Rutas;

import java.util.ArrayDeque;
import java.util.Deque;

import Robots.Direccion;
import Talos.Laberinto;

/**
 * Clase GenerarRutaDerecha, genera rutas mediante el algoritmo de la mano
 * derecha.
 * @version 0.1 11/12/2014
 * @author Grupo Talos { Jorge Bote Albal�, Juan Jose Ram�n Rodr�guez }
 */
public class GenerarRutaDerecha implements GeneradorRutas {
	
	@Override
	public Deque<Direccion> generarRuta() {
		Deque<Direccion> ruta = new ArrayDeque<Direccion>();
		Laberinto laberinto = Laberinto.getInstancia();
		int id_sala = 0, id_siguiente = -1, i = 0;
		boolean hacia_atras = false;
		Direccion atras = null, aux = null;
		Direccion dir_siguiente[] = {Direccion.N, Direccion.O, Direccion.S, Direccion.E};
		while(id_sala != laberinto.getAncho()*laberinto.getAlto()-1){
			if (dir_siguiente[i] != atras && !hacia_atras){
				switch (dir_siguiente[i]) {
				case N:
					id_siguiente = id_sala - laberinto.getAncho();
					aux = Direccion.S;
					break;
				case O:
					id_siguiente = id_sala - 1;
					aux = Direccion.E;
					break;
				case S:
					id_siguiente = id_sala + laberinto.getAncho();
					aux = Direccion.N;
					break;
				case E:
					id_siguiente = id_sala + 1;
					aux = Direccion.O;
					break;
				}
				if(laberinto.obtenerSiAdyacente(id_sala, id_siguiente)){
					ruta.add(dir_siguiente[i]);
					if (dir_siguiente[i] == Direccion.N || dir_siguiente[i] == Direccion.E)
						hacia_atras = true;
					id_sala = id_siguiente;
					atras = aux;
					i = 0;
				}
				else{
					i++;
					if (i == 4)
							i = 0;
				}
			}
			else{
				switch (atras) {
				case N:
					i = 1; 
					break;
				case O:
					i = 2; 
					break;
				case S:
					i = 3; 
					break;
				case E:
					i = 1;
					break;
				}
				atras = null;
				hacia_atras = false;
			}
		}
		return ruta;
	}
		
}