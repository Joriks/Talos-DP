package Talos;

/**
 * Clase Llave, dispone de un código numérico que permite identificarla.
 * @version 0.2 06/10/2014
 * @author Grupo Talos { Jorge Bote Albalá, Juan Jose Ramón Rodríguez }
 */
public class Llave implements Comparable<Llave>{
	
	/** Identificador de la clase Llave */
	int id;
	
	public Llave() {
		id = 0;
	}
	
	public Llave(int id){
		this.id = id;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int compareTo(Llave o) {
		if(this.id < o.id)
			return -1;
		if(this.id > o.id)
			return 1;
		return 0;
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Llave))
    		return false;
    	Llave l = (Llave) o; 
    	return (id == l.id);
	}
	
	@Override
	public String toString() {
		return Integer.toString(id);
	}
}
