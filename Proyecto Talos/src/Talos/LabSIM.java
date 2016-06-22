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
		
	setOutputStream("registro.log", "error.log");
		
		/**  
		instancia asociada al fichero de entrada inicio.txt
		*/		
		Cargador cargador = new Cargador();
		try {
			/**  
			MŽtodo que procesa l’nea a l’nea el fichero de entrada inicio.txt
			*/
		     FicheroCarga.procesarFichero("inicio.txt", cargador);

		     Laberinto laberinto = Laberinto.getInstancia();

		     laberinto.simular();

		}
		catch (FileNotFoundException valor)  {
			System.err.println ("Excepción Capturada al procesar el fichero: " 
		+ valor.getMessage());
			System.err.println("Debe existir el fichero inicio.txt o renombrar "
					+ "los posibles inicio10x10 inicio10x6 o inicio6x6 a inicio.txt");
		}
		catch (IOException valor)  {
			System.err.println ("Excepción Capturada al procesar el fichero: " 
		+ valor.getMessage());
			System.err.println("El fichero inicio.txt se ha encontrado pero no"
					+ "se ha podido abrir y/o leer");
		}
		
		
		System.out.close();
		System.err.close();
	}
}
