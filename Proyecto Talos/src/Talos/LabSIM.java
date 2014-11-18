package Talos;

import java.io.FileNotFoundException;
import java.io.IOException;

import Cargador.Cargador;
import Cargador.FicheroCarga;

public class LabSIM {
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
		
		Laberinto lab = cargador.obtenerLaberino();
		
		int[] id_salas_llaves = {3,4,6,8,9,10,11,12,13};
		int[] llaves_sala = {0,1,1,2,3,3,4,5,5,6,7,7,8,9,9,10,11,11,12,13,13,14
				,15,15,16,17,17,18,19,19,20,21,21,22,23,23,24,25,25,26,27,27,28
				,29,29};
		int[] combinacion = {1,3,5,7,9,11,13,15,17,19,21,23,25,27,29};
		
		lab.distribuirLlaves(id_salas_llaves, llaves_sala);
		lab.configurarPuerta(combinacion);
		
		lab.simular();
	}
}
