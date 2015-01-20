package Rutas;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import Estructuras.Grafo;
import Robots.Direccion;
import Talos.Laberinto;

/**
 * Clase GenerarRutaProfunda, genera rutas mediante el uso de grafos y el
 * algoritmo de profundidad.
 * @version 0.1 11/12/2014
 * @author Grupo Talos { Jorge Bote Albalá, Juan Jose Ramón Rodríguez }
 */
public class GenerarRutaProfunda implements GeneradorRutas {

	@Override
	public Deque<Direccion> generarRuta() {
		Laberinto l = Laberinto.getInstancia();
		Grafo g = l.obtenerGrafo();
		Queue<Integer> visitados = new ArrayDeque<Integer>();
		List<Integer> ruta = new LinkedList<Integer>();
		profundidad(g, 0, 35, visitados, ruta);
		return generarDirecciones(ruta);
	}

	private boolean profundidad(Grafo g, int vertice, int salida,
			Queue<Integer> visitados, List<Integer> ruta) {
		if(vertice == salida)
			return true;
		boolean flag = false;
		Set<Integer> ady = new LinkedHashSet<Integer>();

		visitados.add(vertice);
		ruta.add(vertice);
		g.adyacentes(vertice, ady);
		for (Integer adyacente : ady) {
			if(!visitados.contains(adyacente) && !flag){
				if(adyacente == salida){
					visitados.add(salida);
					ruta.add(adyacente);
					flag = true;
				}
				else{
					flag = profundidad(g, adyacente, salida, visitados, ruta);
				}
			}
		}
		if(!flag)
			ruta.remove((Integer)vertice);
		return flag;
	}

	private Deque<Direccion> generarDirecciones(List<Integer> ruta) {
		Deque<Direccion> movimientos = new ArrayDeque<Direccion>();
		Laberinto l = Laberinto.getInstancia();
		int ancho = l.getAncho();
		int anterior = ruta.remove(0);
		
		for (Integer sala : ruta) {
			if(sala - ancho == anterior)
				movimientos.add(Direccion.S);
			else if (sala + ancho == anterior) {
				movimientos.add(Direccion.N);
			}
			else if (sala + 1 == anterior) {
				movimientos.add(Direccion.O);
			}
			else if (sala - 1 == anterior) {
				movimientos.add(Direccion.E);
			}
			anterior = sala;
		}
		return movimientos;
	}
}