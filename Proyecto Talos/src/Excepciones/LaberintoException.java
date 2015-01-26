package Excepciones;

public class LaberintoException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -399644130709432620L;

	public LaberintoException(String string) {
		super(string);
	}
	
	public String getConfiguracionMessage(){
		
		return getMessage() + " El laberinto tiene parametros de configuraci�n "
				+ "no permitidos";
		}
}
