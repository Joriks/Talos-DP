package Talos;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.CopyOnWriteArrayList;

import Robots.Robot;

/**
 * Clase Sala, guarda información sobre una sala del laberinto.
 * @version 0.2 30/10/2014
 * @author Grupo Talos { Jorge Bote Albalá, Juan Jose Ramón Rodríguez }
 */
public class Sala {
	
	/** Guarda el identificador de la sala*/
	private int id_sala;
	
	/** Lista de llaves guardadas en la sala*/
	private LinkedList<Llave> llaves_sala;
	
	/** Cola de robots que se encuentran en la sala*/
	private Deque<Robot> robots_sala;
	
	/** Marca que indica la conexión entre salas*/
	private int marca;

	/** Información de la puerta, si la tiene*/
	private Puerta puerta;
	
	/**
	 * Constructor parametrizado con un identificador de la clase Sala
	 * PRE:
	 * POST: Crea una sala con el identificador dado;
	 * Complejidad: O(1);
	 */
	public Sala(int id){
		id_sala = id;
		llaves_sala = new LinkedList<Llave>();
		robots_sala = new ArrayDeque<Robot>();
		puerta = null;
	}
	
	/**
	 * Constructor parametrizado con un identificador de la clase Sala y la lista de las llaves.
	 * PRE:
	 * POST: Crea una sala con el identificador y las llaves dadas;
	 * Complejidad: O(1);
	 */
	public Sala(int id, LinkedList<Llave> llaves_sala){
		id_sala = id;
		this.llaves_sala = llaves_sala;
		robots_sala = new ArrayDeque<Robot>();
		puerta = null;
	}
	
	/**
	 * Obtiene la marca de kruskal de la sala.
	 * PRE:
	 * POST:
	 * @return entero, Marca de kruskal
	 */
	public int getMarca() {
		return marca;
	}

	/**
	 * Inserta la nueva marca de kruskal en la sala.
	 * PRE:
	 * POST:
	 * @param marca, Marca de kruskal
	 */
	public void setMarca(int marca) {
		this.marca = marca;
	}
	
	/**
	 * Metodo para obtener la puerta de la sala.
	 * PRE: La sala debe estar inicializada
	 * POST: Si la sala tiene puerta la devuelve
	 * @return Puerta
	 * Complejidad: O(1)
	 */
	public Puerta obtenerPuerta(){
		return puerta;
	}
	
	/**
	 * Metodo para configurar inicialmente la puerta de la sala.
	 * PRE: La sala debe estar inicializada y los parámetros de configuración de puerta ser correctos.
	 * POST: Se configura la puerta de la sala
	 * @param combinacion Combinación con la que configurar la puerta
	 * @param altura Altura del árbol con la que configurar la puerta
	 * Complejidad: O(n)
	 */
	public void configurarPuerta(int[] combinacion, int altura){
		puerta = new Puerta(combinacion, altura);
	}
	
	/**
	 * Metodo que saca y devuelve la primera llave a sacar
	 * PRE: La sala debe estar inicializada
	 * POST: Decrementa en uno el número de llaves y la devuelve
	 * @return la llave que saca
	 * Complejidad: O(1)
	 */
	public Llave sacarLlave(){	
		return llaves_sala.pollFirst();
	}
	
	/**
	 * Metodo que introduce una llave en la sala 
	 * PRE: La sala debe estar inicializada
	 * POST: Añade una llave a la sala
	 * @param llave Llave a meter en la sala
	 * Complejidad: O(n)
	 */
	public void meterLlave(Llave llave){
		llaves_sala.add(llave);
		Collections.sort(llaves_sala);
	}
	
	/**
	 * Metodo que saca y devuelve el primer robot a salir de la sala
	 * PRE: La sala debe estar inicializada
	 * POST: Saca un robot
	 * @return Robot El robot que sale
	 * Complejidad: O(1)
	 */
	public Robot sacarRobot(){
		return robots_sala.poll();
	}
	
	/**
	 *  Metodo que introduce un robot en la sala
	 * PRE: La sala debe estar inicializada y el robot ser correcto
	 * POST: Introduce el robot
	 *  Complejidad: O(1)
	 */
	public void meterRobot(Robot robot){
		robots_sala.addLast(robot);
	}
	
	/**
	 * Metodo para actualizar el turno de los robots de la sala.
	 * PRE: La sala debe estar inicializada y el turno actual ser correcto, mayor o igual que cero
	 * POST: Simula un turno en sala
	 * @param turno_actual Información sobre el turno actual en la simulación
	 * Complejidad: O(n log n)
	 */
	public void simularTurno(int turno_actual){
		//Optimizar con un contador o dejar con el copyonwirte
		CopyOnWriteArrayList<Robot> robots = new CopyOnWriteArrayList<Robot>(robots_sala);
		for(Robot robot : robots)
			robot.simularTurno(turno_actual);
	}
	
	/**
	 * Metodo que obtiene un string. Si solo hay un robot devuelve la marca, si hay varios devuelve el número de robots y un guión si no hay robots 
	 * PRE: La sala debe estar inicializada
	 * POST: Devuelve un string con las marca de un robot, el número de los robots en sala o un guión
	 * @return String
	 * Complejidad: O(1)
	 */
	public String robotsEnSala(){
		if(robots_sala.size() == 1){
			char marca = robots_sala.getFirst().obtenerMarca();
			return Character.toString(marca);
		}
		else
			if(robots_sala.size() == 0)
				return "_";
			else
				return Integer.toString(robots_sala.size());
	}
	
	/**
	 * Metodo que devuelve el string de los robots ganadores
	 * PRE: La sala debe estar inicializada
	 * POST: 
	 * @return String
	 * Complejidad: O(1)
	 */
	public String stringGanadores(){
		return "(robotsganadores)\n" + robots_sala.toString().replace(", ", "\n")
				.replace("[", "").replace("]", "");
	}

	@Override
	public String toString() {
		return "(sala:" + id_sala + ":" + llaves_sala.toString().replace(", ", "\n")
				.replace("[", "").replace("]", "") + ")";
	}
}
