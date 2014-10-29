package Robots;

import Talos.Puerta;
import Talos.Sala;

public class Asimo extends Robot{

	public Asimo() {
		super("asimo",'\0', 0, 0);
	}
	
	public Asimo(char marca, int turno, int sala_actual){
		super("asimo", marca, turno, sala_actual);
	}
	
	@Override
	protected void interactuarPuerta(Puerta puerta) {
		System.out.println(nombre + ":" + marca + ":" + "Cierro puerta");
	}

	@Override
	protected void interactuarLlave(Sala sala) {
		sala.insertarLlave(llaves.pollLast());
		System.out.println(nombre + ":" + marca + ":" + "Suelto llave");
	}
}
