package Robots;

/**
 * Clase Sonny, hereda de la clase robot.
 * @version 0.2 30/10/2014
 * @author Grupo Talos { Jorge Bote Albal�, Juan Jose Ram�n Rodr�guez }
 */
public class Sonny extends Robot{

	public Sonny() {
		super("sonny",'\0', 0, 0);
	}
	
	public Sonny(String nombre, char marca, int turno, int sala_actual){
		super(nombre, marca, turno, sala_actual);
	}
}
