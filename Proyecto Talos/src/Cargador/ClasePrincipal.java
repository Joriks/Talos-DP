package Cargador;
/**
 * Clase creada para ser usada en la utilidad cargador
 * contiene el main del cargador. Se crea una instancia de la clase Laberinto, una instancia de la clase Cargador
 * y se procesa el fichero de inicio, es decir, se leen todas las l’neas y se van creando todas las instancias de la simulaci—n
 * 
 * @version 4.0 -  15/10/2014 
 * @author Profesores DP
 */
import java.io.FileNotFoundException;
import java.io.IOException;

import Talos.Laberinto;

public class ClasePrincipal {
	public static void main(String[] args) {
		/**  
		instancia asociada al fichero de entrada inicio.txt
		*/
		Cargador cargador = new Cargador();
		try {
			/**  
			MŽtodo que procesa l’nea a l’nea el fichero de entrada inicio.txt
			*/
		     FicheroCarga.procesarFichero("inicio.txt", cargador);
		}
		catch (FileNotFoundException valor)  {
			System.err.println ("Excepci—n capturada al procesar fichero: "+valor.getMessage());
		}
		catch (IOException valor)  {
			System.err.println ("Excepci—n capturada al procesar fichero: "+valor.getMessage());
		}
		Laberinto l = cargador.obtenerLaberino();
		int[] id_salas_llaves = {3,4,6,8,9,10,11,12,13};
		int[] llaves_sala = {0,1,1,2,3,3,4,5,5,6,7,7,8,9,9,10,11,11,12,13,13,14
				,15,15,16,17,17,18,19,19,20,21,21,22,23,23,24,25,25,26,27,27,28
				,29,29};
		int[] combinacion = {1,3,5,7,9,11,13,15,17,19,21,23,25,27,29};
		l.distribuirLlaves(id_salas_llaves, llaves_sala);
		l.configurarPuerta(combinacion);
//		System.out.println(l.toString());
	}
}
