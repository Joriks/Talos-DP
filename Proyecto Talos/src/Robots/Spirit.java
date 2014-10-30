package Robots;

/**
 * Clase Spirit, hereda de la clase robot.
 * @version 0.2 30/10/2014
 * @author Grupo Talos { Jorge Bote Albalá, Juan Jose Ramón Rodríguez }
 */
public class Spirit extends Robot{

	public Spirit() {
		super("spirit",'\0', 0, 0);
	}
	
	public Spirit(char marca, int turno, int sala_actual){
		super("spirit", marca, turno, sala_actual);
	}
}
