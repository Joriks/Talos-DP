package Talos;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Set;

import Estructuras.Grafo;
import Excepciones.LaberintoException;
import Robots.Robot;
import Utilidades.GenAleatorios;

/**
 * Clase Laberinto, guarda las salas del mapa y ejecuta la simulacion
 * @version 0.2 30/10/2014
 * @author Grupo Talos { Jorge Bote Albal�, Juan Jose Ram�n Rodr�guez }
 */
public class Laberinto {

	/** Guarda el ancho de laberinto */
	private int ancho;
	
	/** Guarda el alto de laberinto */
	private int alto;
	
	/** Guarda la altura del �rbol a partir de la cual se abre la puerta del laberinto */
	private int altura_arbol;
	
	/** Identificador de la sala donde est� la puerta de salida del laberint o*/
	private int sala_puerta;

	/** Identificador de la sala de victoria donde se almacenan los robots que 
	 * consiguen salir del laberinto */
	public static final int sala_ganadores = 1111;
	
	/** HashMap con la informaci�n de las salas del laberinto */
	private HashMap<Integer, Sala> salas;
	
	/** Grafo con los caminos posibles entre las salas*/
	private Grafo caminos;
	
	/** N�mero m�ximo de turnos de una simulaci�n del laberinto */
	private int max_turnos;
	
	/** Turno actual de la simulaci�n */
	private int turno_actual;

	/** Laberinto siguiendo el patr�n Singleton*/
	private static Laberinto instancia;

	/**
	 * Constructor por defecto de la clase Laberinto
	 * PRE:
	 * POST: Crea un laberinto con los valores por defecto (vac�o);
	 * Complejidad: O(1);
	 */
	private Laberinto(){
		ancho = 0;
		alto = 0;
		altura_arbol = 0;
		sala_puerta = 0;
		salas = new HashMap<Integer, Sala>();
		caminos = new Grafo();
		max_turnos = 0;
	}
	
	/**
	 * Obtener la instancia del laberinto
	 * PRE: 
	 * POST: Devuelve la instancia, de tipo est�tico, del laberinto
	 * @return Laberinto
	 * Complejidad: O(1);
	 */
	public static Laberinto getInstancia(){
		if(instancia == null)
			instancia = new Laberinto();
		return instancia;
	}
	
	/**
	 * Obtener el ancho del laberinto
	 * PRE: El laberinto debe estar creado correctamente
	 * POST: Devuelve el ancho del laberinto
	 * @return int
	 * Complejidad: O(1);
	 */
	public int getAncho() {
		return ancho;
	}

	/**
	 * Obtener el alto del laberinto
	 * PRE: El laberinto debe estar creado correctamente
	 * POST: Devuelve el alto del laberinto
	 * @return int
	 * Complejidad: O(1);
	 */
	public int getAlto() {
		return alto;
	}
	
	/**
	 * Obtiene la sala indicada por parametro
	 * PRE: El laberinto debe estar creado correctamente y los parametros ser correctos
	 * @param id_sala, identificador de la sala a obtener
	 * @return devuelve la sala pedida
	 */
	public Sala getSala(int id_sala){
		return salas.get(id_sala);
	}
	
	/**
	 * Metodo para configurar el laberinto inicial.
	 * PRE: El laberinto debe estar creado y los par�metros ser correctos
	 * POST: Configura el laberinto con los datos 
	 * @param sala_puerta Identificador de la sala con la puerta de laberinto
	 * @param dim_x El ancho con el que configurar del laberinto
	 * @param dim_y El alto con el que configurar del laberinto
	 * Complejidad: O(n)
	 * @throws LaberintoException 
	 */
	public void configurarLaberinto(int sala_puerta, int dimX, int dimY, 
			int altura_arbol) throws LaberintoException {
		if(dimX < 0 || dimY < 0 || altura_arbol < 0 || sala_puerta < 0 ||
				sala_puerta > (dimX*dimY) || (dimX*dimY) < 9)
			throw new LaberintoException("(laberinto:" + ancho + ":" + alto +
					":" + sala_puerta + ":" + altura_arbol + ")");
		ancho = dimX;
		alto = dimY;
		this.altura_arbol = altura_arbol;
		this.sala_puerta = sala_puerta;
		for(int i=0; i<dimX*dimY; i++){
			salas.put(i, new Sala(i));
			caminos.nuevoNodo(i);
		}
		salas.put(sala_ganadores, new Sala(sala_ganadores));
		max_turnos = 50;
		
		configurarParedes();
		distribuirLlaves();
		
		int[] combinacion = {1,3,5,7,9,11,13,15,17,19,21,23,25,27,29};
		configurarPuerta(combinacion);
		pintarMapa();
	}
	
	/**
	 * Metodo que configura las paredes del tablero
	 * PRE: El laberinto debe estar creado correctamente y los parametros ser correctos
	 * POST:
	 * Complejidad: O(n)
	 */
	private void configurarParedes() {
		LinkedList<Pared> paredes = new LinkedList<Pared>();
		generarParedes(paredes);
		tirarParedes(paredes);
		generarAtajos();
	}

	/**
	 * Metodo que genera todas las paredes del tablero
	 * PRE: El laberinto debe estar creado correctamente y los parametros ser correctos
	 * POST:
	 * @param listaparedes
	 * Complejidad: O(n)
	 */
	private void generarParedes(LinkedList<Pared> paredes) {
		for(int i = 0;i<alto*ancho;i++){
			if(i-ancho >= 0)//Arriba
				paredes.add(new Pared(i,i-ancho));
			if(i%ancho != ancho-1)//Derecha
				paredes.add(new Pared(i,i+1));
			if(i+ancho < ancho*alto)//Abajo
				paredes.add(new Pared(i,i+ancho));
			if(i%ancho != 0)//Izquierda
				paredes.add(new Pared(i,i-1));
		}
	}
	
	/**
	 * Metodo que utiliza el algoritmo de kruskal para eliminar paredes del tablero
	 * PRE: El laberinto debe estar creado correctamente y los parametros ser correctos
	 * POST:
	 * @param listaparedes
	 * Complejidad O(n)
	 */
	private void tirarParedes(LinkedList<Pared> paredes){
		while(!paredes.isEmpty()){
			int num_pared = GenAleatorios.generarNumero(paredes.size());
//			System.out.println(GenAleatorios.getNumGenerados() + " " + 
//			listaparedes.size() + " " + num_pared);
			Pared pared = paredes.get(num_pared);
			paredes.remove(num_pared);
			int sala_a = pared.getSalaA();
			int sala_b = pared.getSalaB();
			if(obtenerSala(sala_a).getMarca() != obtenerSala(sala_b).getMarca()){
				caminos.nuevoArco(sala_a, sala_b, 1);
				caminos.nuevoArco(sala_b, sala_a, 1);
				propagarMarca(obtenerSala(sala_a).getMarca(),
						obtenerSala(sala_b).getMarca());
			}
		}
	}

	/**
	 * Metodo que propaga la marca A a todas las salas que tengan la marca B.
	 * PRE: El laberinto debe estar creado correctamente y los parametros ser correctos
	 * POST:
	 * @param marca_a, marca de la sala A
	 * @param marca_b, marca de la sala B
	 * Complejidad: O(n)
	 */
	private void propagarMarca(int marca_a, int marca_b) {
		for (Entry<Integer, Sala> sala : salas.entrySet()) {
			if(sala.getValue().getMarca() == marca_b)
				sala.getValue().setMarca(marca_a);
		}
	}

	/**
	 * Metodo que elimina el 5% del numero de salas de las paredes existentes.
	 * PRE: El laberinto debe estar creado correctamente y los parametros ser correctos
	 * POST:
	 * Complejidad:O(n)
	 */
	private void generarAtajos() {
		int num_paredes_tirar = ancho*alto*5/100;
		
		caminos.floyd();
		for(int i = 0; i<num_paredes_tirar;){
			int sala = GenAleatorios.generarNumero(salas.size()-1);
			boolean derrumbada_pared = false;
//			System.out.println("Atajo Sala: "+sala);
			if(derrumbarPared(sala, sala - ancho))
				derrumbada_pared = true;
			else
				if(derrumbarPared(sala, sala + ancho) && !derrumbada_pared)
					derrumbada_pared = true;
				else
					if((sala/ancho == (sala-1)/ancho) && derrumbarPared(sala, sala - 1) && !derrumbada_pared)
						derrumbada_pared = true;
					else
						if((sala/ancho == (sala+1)/ancho) && derrumbarPared(sala, sala + 1) && !derrumbada_pared)
							derrumbada_pared = true;
			if(derrumbada_pared){
				i++;
				caminos.floyd();
			}
		}
		caminos.warshall();
	}
	
	/**
	 * Derrumba la pared entre dos salas
	 * PRE: El laberinto debe estar creado correctamente y los parametros ser correctos
	 * @param sala, sala A
	 * @param sala_contigua, sala B
	 * @return devuelve true si tira la pared, false en caso contrario.
	 * Complejidad:O(1)
	 */
	private boolean derrumbarPared(int sala, int sala_contigua) {
		if(sala >= 0 && sala < (ancho * alto) && sala_contigua >= 0 && sala_contigua < (ancho * alto)){
			if((!caminos.adyacente(sala, sala_contigua))){
				if((caminos.floydC(sala,sala_contigua)) > 3){
					caminos.nuevoArco(sala, sala_contigua, 1);
					caminos.nuevoArco(sala_contigua, sala, 1);
//					System.out.println("Pared Tirada: " + sala + "-" + sala_contigua);
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Distribuye un conjunto de llaves a las salas indicadas.
	 * PRE: El laberinto debe estar creado correctamente y los parametros ser correctos
	 * POST: Las llaves indicadas son distribuidas en las salas que se indican
	 * @param id_salas_llaves Array con los identificadores de salas en las que
	 * distribuir las llaves
	 * @param llaves_sala Array de llaves a distribuir
	 * Complejidad: O(n^2)
	 */
	private void distribuirLlaves(){
		Deque<Llave> llaves = new ArrayDeque<Llave>();
		for(int i = 0;i<30;i++){
			llaves.add(new Llave(i));
			if(i%2 != 0)
				llaves.add(new Llave(i));
		}
		Set<Integer> visitadas = new HashSet<Integer>();
		salasMayorFrecuencia(0, visitadas);
			
		Deque<Sala> salas_distribucion = obtenerMayoresSalas();

		while(!llaves.isEmpty()){
			Sala sala = salas_distribucion.poll();
			for(int i = 0;i<5;i++){
				if(sala.getId() != 0 && sala.getId() != sala_puerta)
					sala.meterLlave(llaves.poll());
			}
		}
	}
	
	/**
	 * Modifica las frecuencias de las salas para distribuir las llaves.
	 * PRE: El laberinto debe estar creado correctamente y los parametros ser correctos
	 * @param vertice, sala por donde va el camino
	 * @param visitadas, conjunto de salas visitadas.
	 * Complejidad:O(n)
	 */
	private void salasMayorFrecuencia(int vertice, Set<Integer> visitadas) {
		if(vertice == sala_puerta){
			for(Integer ady: visitadas){
				Sala sala = salas.get(ady);
				sala.incrementarFrecuencia();
			}
		}
		
		visitadas.add(vertice);
		Set<Integer> adyacentes = new HashSet<Integer>();
		caminos.adyacentes(vertice, adyacentes);
		for(Integer ady: adyacentes){
			if(!visitadas.contains(ady)){
				salasMayorFrecuencia(ady, visitadas);
				visitadas.remove(ady);
			}
		}
	}
	
	/**
	 * Obtiene una cola con las salas de mayor frecuencia del laberinto.
	 * PRE: El laberinto debe estar creado correctamente y los parametros ser correctos
	 * @return cola de salas de mayor frecuencia.
	 * Complejidad:O(n)
	 */
	private Deque<Sala> obtenerMayoresSalas(){
		Deque<Sala> salas_mayores = new ArrayDeque<Sala>();
		Deque<Sala> salas_frecuencia = new ArrayDeque<Sala>();
		for(Entry<Integer, Sala> par_sala: salas.entrySet())
			salas_frecuencia.add(par_sala.getValue());
		
		salas_frecuencia.pollFirst();
		salas_frecuencia.pollLast();
		
		for(int i = 0;i<9;i++){
			Sala sala_max = salas_frecuencia.element();
			for(Sala sala: salas_frecuencia){
				if(sala.getFrecuencia() > sala_max.getFrecuencia())
					sala_max = sala;
			}
			salas_frecuencia.remove(sala_max);
			salas_mayores.addLast(sala_max);
		}
		return salas_mayores;
	}

	/**
	 * Configura la puerta de la sala de salida
	 * PRE: El laberinto debe estar creado correctamente y la combinacion ser v�lida
	 * POST: se configura la puerta con la combinaci�n dada
	 * @param combinacion Combinaci�n con la que configurar la puerta
	 * Complejidad: O(n)
	 */
	private void configurarPuerta(int[] combinacion){
		Sala sala = salas.get(sala_puerta);
		sala.configurarPuerta(combinacion, altura_arbol);
	}

	/**
	 * Inserta el robot dado en su sala correspondiente.
	 * PRE: El laberinto debe estar creado correctamente y el robot ser correcto
	 * POST: Mete el robot en el laberinto
	 * @param robot
	 * Complejidad: O(1)
	 */
	public void meterRobot(Robot robot){
		Sala sala = salas.get(robot.obtenerSala());
		sala.meterRobot(robot);
	}
	
	/**
	 * Realiza la simulaci�n completa del laberinto.
	 * PRE: El laberinto debe estar creado correctamente
	 * POST: Realiza la simulaci�n del laberinto
	 * Complejidad: O(n^2)
	 */
	public void simular(){
		System.out.println("(distribucion llaves)");
		pintarSalas();
		pintarRutas();
		Sala s = salas.get(sala_puerta);
		Puerta p = s.obtenerPuerta();
		turno_actual = 0;
		while(turno_actual<max_turnos && p.estadoPuerta() == Estados.cerrada){
			simularTurno(turno_actual);
			turno_actual++;
		}
		if(p.estadoPuerta() == Estados.abierta)
			System.out.println(salas.get(sala_ganadores).stringGanadores());
	}
	
	/**
	 * M�dulo privado para simular un turno completo en el laberinto
	 * PRE: El laberinto debe estar creado correctamente
	 * POST: Realiza la simulaci�n de un turno en el laberinto
	 * Complejidad: O(n)
	 * @param turno
	 */
	private void simularTurno(int turno){
		for(Entry<Integer, Sala> par_sala : salas.entrySet()){
			par_sala.getValue().simularTurno(turno);
		}
		pintarLaberinto(turno);
	}
	
	/**
	 * Pinta el laberinto completo, con la configuracion del laberinto
	 * el tablero completo, las salas que tienen llaves, y los robots de las
	 * salas.
	 * PRE: El laberinto debe estar creado correctamente y los parametros ser correctos
	 * @param turno, turno actual de la simulacion
	 * Complejidad:O(n)
	 */
	private void pintarLaberinto(int turno) {
		System.out.println(this.toString());
		pintarMapa();
		pintarSalas();
		pintarRobots();
	}

	/**
	 * Pinta un laberinto 2D y muestra el estado de este.
	 * PRE: El laberinto debe ser correcto
	 * POST: Pinta el estado actual del laberinto.
	 * Complejidad: O(n^2)
	 */
	public void pintarMapa(){
		for(int i = 0;i<ancho;i++)
			System.out.print(" _");
		
		System.out.println("");
		
		for(int i = 0;i<alto;i++){
			System.out.print("|");
			for(int j = 0;j<ancho;j++){
				Sala s = salas.get(ancho*i+j);
				int robots_sala = s.robotsEnSala();
				String celda_sala = "";
				switch (robots_sala) {
				case 0:
					if(caminos.adyacente(ancho*i+j, (ancho*i+j)+ancho))
						celda_sala = " ";
					else
						celda_sala = "_";
					break;
				default:
					celda_sala+=s.robotsSala();

					break;
				}
				if(caminos.adyacente(ancho*i+j, ancho*i+j+1))
					celda_sala+=" ";
				else
					celda_sala+="|";
				System.out.print(celda_sala);
			}
			System.out.println("");
		}
	}

	/**
	 * Pinta las salas que tienen llaves
	 * PRE: El laberinto debe estar creado correctamente y los parametros ser correctos
	 * Complejidad:O(n)
	 */
	private void pintarSalas() {
		for(Entry<Integer, Sala> par_sala : salas.entrySet()){
			Sala sala = par_sala.getValue();
			if(sala.tieneLlaves())
				System.out.println(sala.toString());
		}
	}

	/**
	 * Pinta los robots del laberinto
	 * PRE: El laberinto debe estar creado correctamente y los parametros ser correctos
	 * Complejidad:O(n)
	 */
	private void pintarRobots() {
		for(Entry<Integer, Sala> par_sala : salas.entrySet()){
			Sala sala = par_sala.getValue();
			if(sala.robotsEnSala() > 0 && par_sala.getKey() != sala_ganadores)
				sala.pintarRobots();
		}
	}
	
	/**
	 * Pinta las rutas de los robots que van a entrar en el laberinto
	 * PRE: El laberinto debe estar creado correctamente y los parametros ser correctos
	 * Complejidad:O(n)
	 */
	private void pintarRutas(){
		for(Entry<Integer, Sala> par_sala : salas.entrySet()){
			Sala sala = par_sala.getValue();
			if(sala.robotsEnSala() > 0 && par_sala.getKey() != sala_ganadores)
				sala.pintarRutas();
		}
	}
	
	/**
	 * Comprueba si una sala dada tiene puerta.
	 * PRE: El laberinto debe estar creado correctamente y el identificador
	 * de la sala a consultar ser correcto
	 * POST: Devuelve si la sala indicada tiene puerta o no tiene
	 * @param id_sala
	 * @return boolean True si tiene puerta y false en caso contrario
	 * Complejidad: O(n)
	 */
	public boolean tienePuerta(int id_sala) {
		Sala s = salas.get(id_sala);
		return (s.obtenerPuerta() == null) ? false : true;
	}

	/**
	 * Obtiene la puerta de una sala dada si la tiene
	 * PRE: El laberinto debe estar creado correctamente y el identificador
	 * de la sala a consultar ser correcto
	 * POST: Si la sala indicada tiene puerta la devuelve
	 * @param id_sala
	 * @return Puerta
	 * Complejidad: O(1)
	 */
	public Puerta obtenerPuerta(int id_sala) {
		Sala s = salas.get(id_sala);
		return s.obtenerPuerta();
	}

	/**
	 * Obtiene una sala dado su identificador.
	 * PRE: El laberinto debe estar creado correctamente y el identificador
	 * de la sala a consultar ser correcto
	 * POST: Devuelve la sala con el identificador dado
	 * @param id_sala
	 * @return Sala
	 * Complejidad: O(1)
	 */
	public Sala obtenerSala(int id_sala) {
		return salas.get(id_sala);
	}
	
	/**
	 * Comprueba si la puerta de salida esta abierta o no
	 * PRE: El laberino debe estar inicializado y debe existir una puerta
	 * POST:
	 * @return retorna true si la puerta est� abierta, false en caso contrario.
	 * Complejidad:O(1)
	 */
	public boolean puertaAbierta() {
		Sala s = salas.get(sala_puerta);
		Puerta p = s.obtenerPuerta();
		return (p.estadoPuerta() == Estados.abierta);
	}
	
	/**
	 * Obtiene el identificador de la sala siguiente en un camino minimo
	 * PRE: El laberinto debe estar creado correctamente y los parametros ser correctos
	 * @param origen, sala origen
	 * @param destino, sala destino
	 * @return devuelve el identificador de la sala intermedia
	 */
	public int obtenerSiguiente(int origen, int destino){
		return caminos.siguiente(origen, destino);
	}
	
	/**
	 * Comprueba si dos salas son adyacentes
	 * PRE: El laberinto debe estar creado correctamente y los parametros ser correctos
	 * @param origen, sala origen
	 * @param destino, sala destino
	 * @return retorna true si son adyacentes, false en caso contrario
	 */
	public boolean obtenerSiAdyacente(int origen, int destino){
		return caminos.adyacente(origen, destino);
	}

	/**
	 * Obtiene un conjunto de salas adyacentes a la sala indicada.
	 * PRE: El laberinto debe estar creado correctamente y los parametros ser correctos
	 * @param origen, sala origen
	 * @param ady, conjunto de salas adyacentes.
	 * Complejidad:O(n)
	 */
	public void adyacentes(int origen, Set<Integer> ady) {
		caminos.adyacentes(origen, ady);
	}
	
	@Override
	public String toString() {
		Sala sala = salas.get(sala_puerta);
		Puerta puerta = sala.obtenerPuerta();
		return "(turno:" + turno_actual + ")\n" + "(laberinto:" + sala_puerta + 
				")\n" + puerta.toString();
	}
}