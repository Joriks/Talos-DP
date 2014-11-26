package Excepciones;

import java.util.List;

public class RobotException extends Exception{
	public RobotException(String string) {
		// TODO Auto-generated constructor stub
		super(string);
	}

	public void configuracionMal(List<String> vCampos, int idSala) {
		String cadenaError = "Robot no insertado: ";
		
		if (Integer.valueOf(vCampos.get(3))<0)
			cadenaError += vCampos.get(1) + " Turno erroneo";
		if (idSala<0)
			System.err.println(" Sala erronea");
		System.err.println(cadenaError);
	}
	
}
