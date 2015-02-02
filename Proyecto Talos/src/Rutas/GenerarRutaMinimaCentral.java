package Rutas;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedHashSet;
import java.util.Set;

import Robots.Direccion;
import Talos.Laberinto;
import Talos.Sala;

public class GenerarRutaMinimaCentral extends GenerarRutaMinima{

	@Override
	public Deque<Direccion> generarRuta() {
		Deque<Direccion> ruta = new ArrayDeque<Direccion>();
		Laberinto laberinto = Laberinto.getInstancia();
		int SE = (laberinto.getAlto()*laberinto.getAncho())-1;
		int NE = laberinto.getAncho()-1;
		int NO = 0;
		int SO = laberinto.getAncho()*(laberinto.getAlto()-1);
		int sala_central = salaCentralSinSalida();
		
		ruta.addAll(camino(SE, sala_central));
		ruta.addAll(camino(sala_central, SO));
		ruta.addAll(camino(SO, SE));
		return ruta;
	}

	private int salaCentralSinSalida() {
		Laberinto l = Laberinto.getInstancia();
		int fila_central = (l.getAlto()%2 != 0) ? (l.getAlto()%2)+1 : l.getAlto();
		int i = l.getAncho()-1;
		int sala_sin_salida = l.getAncho()*fila_central;
		while(i>=0){
			Set<Integer> ady = new LinkedHashSet<Integer>();
			l.adyacentes(l.getAncho()*fila_central+i, ady);
			if(ady.size() == 1)
				sala_sin_salida = l.getAncho()*fila_central+i;
			i--;
		}
		return sala_sin_salida;
	}

}
