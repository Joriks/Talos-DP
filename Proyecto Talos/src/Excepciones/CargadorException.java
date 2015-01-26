package Excepciones;

public class CargadorException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 920020764621423743L;

	public CargadorException(String string) {
		super(string);
	}

	public String getLaberintoMessage(){
		return getMessage() + " Error en el numero de campos para la configuracion"
				+ "del laberinto, necesita 5 campos";
	}
	
	public String getRobotMessage(){
		return getMessage() + " Error en el numero de campos para la configuracion"
				+ "del robot, necesita 4 campos";
	}
	
}
