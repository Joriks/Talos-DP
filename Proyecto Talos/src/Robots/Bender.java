package Robots;

public class Bender extends Robot{
	
	public Bender() {
		super("bender",'\0', 0, 0);
	}
	
	public Bender(char marca, int turno, int sala_actual){
		super("bender", marca, turno, sala_actual);
	}
}
