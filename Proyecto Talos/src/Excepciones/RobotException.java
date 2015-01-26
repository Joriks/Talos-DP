package Excepciones;


public class RobotException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2012051512548880732L;

	public RobotException(String string) {
		super(string);
	}

	public String getConfiguracionMessage(){
		return getMessage() + " El robot tiene parametros de configuración no "
				+ "permitidos";
	}
	
	public String getMovimientoMessage(){
		return getMessage() + " El movimiento indicado en la ruta del robot "
				+ "no coincide con las existentes Direccion {N,S,E,O}";
	}
}
