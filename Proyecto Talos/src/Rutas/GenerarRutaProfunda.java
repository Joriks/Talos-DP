package Rutas;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

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
		Queue<Integer> visitados = new ArrayDeque<Integer>();
		List<Integer> ruta = new LinkedList<Integer>();
		profundidad(0, l.getAlto()*l.getAncho()-1, visitados, ruta);
		return generarDirecciones(ruta);
	}

	/**
	 * Metodo que calcula mediante el algoritmo de profundidad una ruta desde
	 * la sala origen a la sala de salida.
	 * @param vertice, sala por donde va el camino
	 * @param salida, sala de salida del laberinto
	 * @param visitados, conjunto de salas visitadas
	 * @param ruta, lista de salas de la ruta
	 * @return retorna true si encuentra una ruta, false en caso contrario.
	 * Complejidad: O(n log n)
	 */
	private boolean profundidad(int vertice, int salida,
			Queue<Integer> visitados, List<Integer> ruta) {
		if(vertice == salida)
			return true;
		boolean flag = false;
		Set<Integer> ady = new LinkedHashSet<Integer>();

		visitados.add(vertice);
		ruta.add(vertice);
		Laberinto l = Laberinto.getInstancia();
		l.adyacentes(vertice, ady);
		for (Integer adyacente : ady) {
			if(!visitados.contains(adyacente) && !flag){
				if(adyacente == salida){
					visitados.add(salida);
					ruta.add(adyacente);
					flag = true;
				}
				else{
					flag = profundidad(adyacente, salida, visitados, ruta);
				}
			}
		}
		if(!flag)
			ruta.remove((Integer)vertice);
		return flag;
	}

	/**
	 * Dada una lista de enteros, devuelve una cola de direcciones que los robots
	 * pueden seguir y entender.
	 * @param ruta, lista de salas por las que pasa la ruta
	 * @return devuelve una cola de direcciones
	 * Complejidad: O(n)
	 */
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
