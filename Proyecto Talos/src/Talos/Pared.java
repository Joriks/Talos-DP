package Talos;

public class Pared {
	private int sala_a;
	private int sala_b;
	
	public Pared() {
		sala_a = 0;
		sala_b = 0;
	}
	
	public Pared(int sala_a, int sala_b){
		this.sala_a = sala_a;
		this.sala_b = sala_b;
	}
	
	public int getSalaA(){
		return sala_a;
	}
	
	public int getSalaB(){
		return sala_b;
	}
	
	@Override
	public String toString() {
		return "(" + sala_a + "," + sala_b + ")";
	}
}