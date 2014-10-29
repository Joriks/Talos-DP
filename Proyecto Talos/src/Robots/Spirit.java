package Robots;

public class Spirit extends Robot{

	public Spirit() {
		super("spirit",'\0', 0, 0);
	}
	
	public Spirit(char marca, int turno, int sala_actual){
		super("spirit", marca, turno, sala_actual);
	}
}
