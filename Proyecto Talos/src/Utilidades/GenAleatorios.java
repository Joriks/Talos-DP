package Utilidades;

import java.util.Random;

/**
* Generador de nœmeros aleatorios - proyecto12_13
* Implementacion de la clase que permite generar nœmeros aleatorios
* @version 1.0
* @author
* <b> Profesores DP </b><br>
* Asignatura Desarrollo de Programas<br/>
* Curso 11/12
*/
public class GenAleatorios {
	
	/** Instancia de la clase Random que permite generar los nœmeros aleatorios. */
	private static Random r;
	
	/** Constante con la semilla para inicializar la generaci—n de nœmeros aleatorios. ÁÁNo cambiar!! */
	private static final int SEMILLA=1987;		 
	
	/** Contador de nœmeros aleatorios generados */
	private static int numGenerados;			
	
	/** Instancia de la propia clase (s—lo habr‡ una en el sistema). Ver PATRîN DE DISE„O SINGLETON */
	private static GenAleatorios instancia=null;	
	
	/**
	* Constructor por defecto. Inicializa un objeto de tipo GenAleatorio
	* @return Devuelve un objeto de tipo GenAleatorio inicializado
	*/
	private GenAleatorios(){
    	// Inicializaci—n de la semilla para generar los nœmeros aleatorios
    	r = new Random(GenAleatorios.SEMILLA);
    	// Inicializaci—n del atributo que cuenta cu‡ntos nœmeros aleatorios se han generado
    	numGenerados = 0;
    	}
	
	/**
	 * Metodo generarNumero. Genera un nœmero aleatorio entre 0 y (limiteRango-1)
	 * @param limiteRango El l’mite del rango en el que generar los aleatorios
	 * @return Devuelve el nœmero aleatorio generado
	 */
	public static int generarNumero(int limiteRango){
		if (instancia == null) 
			instancia = new GenAleatorios();
		numGenerados++;
		return r.nextInt(limiteRango);
	}
	
	/**
	 * Devuelve el nœmero de aleatorios generados
	 * @return Nœmero de aleatorios generados
	 */
	public static int getNumGenerados(){
		return numGenerados;
	}
	
	public static void main(String[] args) {
		
		for (int i=20; i>10; i--) {
			System.out.println("Nœmero generado (entre 0 y " + i + "): " + GenAleatorios.generarNumero(i));
		}
		System.out.println("Nœmeros generados: " + GenAleatorios.getNumGenerados());
	}	
}
