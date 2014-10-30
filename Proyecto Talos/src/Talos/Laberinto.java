package Talos;

import java.util.HashMap;

public class Laberinto {

	int altura_arbol;
	int columnas;
	int filas;
	int sala_puerta;
	private HashMap<Integer, Sala> salas;

	public Laberinto(int salaPuerta,int dimX,int dimY,int alturaArbol){
		sala_puerta = salaPuerta;
		columnas = dimX;
		filas=dimY;
		altura_arbol=alturaArbol;
		salas = new HashMap<Integer, Sala>();
		for(int i=0; i<dimX*dimY; i++){
			salas.put(i, new Sala(i));
		}
	}
	
	public void distribuirLlaves(int[] id_salas_llaves, int[] llaves_sala){
		for(int i = 0;i<id_salas_llaves.length;i++){
			Sala sala = salas.get(id_salas_llaves[i]);
			for(int x = 0; x<5; x++)
				sala.meterLlave(new Llave(llaves_sala[i*5+x]));
		}
	
	}
	
	public void configurarPuerta(int[] combinacion){
		Sala sala = salas.get(sala_puerta);
		sala.configurarPuerta(combinacion, altura_arbol);
	}
	
	public static void main(String[] args) {
		int[] id_salas_llaves = {3,4,6,8,9,10,11,12,13};
		int[] llaves_sala = {0,1,1,2,3,3,4,5,5,6,7,7,8,9,9,10,11,11,12,13,13,14,15,15,16,17,17,18,19,19,20,21,21,22,23,23,24,25,25,26,27,27,28,29,29};
		Laberinto l = new Laberinto(3, 6, 6, 4);
		l.distribuirLlaves(id_salas_llaves, llaves_sala);
		
		int[] combinacion = {1,3,5,7,9,11,13,15,17,19,21,23,25,27,29};	
		l.configurarPuerta(combinacion);
	}
}