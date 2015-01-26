package Talos;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import Cargador.Cargador;
import Cargador.FicheroCarga;
import Registro.MultiOutputStream;

/**
 * Clase LabSIM, Clase principal del proyecto que se encarga de iniciar todas
 * las instancias del laberinto.
 * @version 0.1 18/11/2014
 * @author Grupo Talos { Jorge Bote Albalá, Juan Jose Ramón Rodríguez }
 */
public class LabSIM {
	
	public static void setOutputStream(String file_out, String file_err){
		try
		{
			FileOutputStream fout= new FileOutputStream(file_out);
			FileOutputStream ferr= new FileOutputStream(file_err);
			
			MultiOutputStream multiOut= new MultiOutputStream(System.out, fout);
			MultiOutputStream multiErr= new MultiOutputStream(System.err, ferr);
			
			PrintStream stdout= new PrintStream(multiOut);
			PrintStream stderr= new PrintStream(multiErr);
			
			System.setOut(stdout);
			System.setErr(stderr);
		}
		catch (FileNotFoundException ex)
		{
			System.err.println("Error al crear los ficheros de log: " + file_out
					+ " y " + file_err);
		}
	}
		
	public static void main(String[] args) {
		
	//setOutputStream("registro.log", "error.log");
		
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
		
		Laberinto laberinto = Laberinto.getInstancia();
		
		int[] combinacion = {1,3,5,7,9,11,13,15,17,19,21,23,25,27,29};
	
		laberinto.configurarPuerta(combinacion);
			
		laberinto.simular();
		
//		System.out.close();
//		System.err.close();
	}
}
