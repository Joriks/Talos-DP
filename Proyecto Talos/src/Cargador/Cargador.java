package Cargador;

import java.util.List;

import Excepciones.CargadorException;
import Excepciones.LaberintoException;
import Excepciones.RobotException;
import Robots.Asimo;
import Robots.AsimoOeste;
import Robots.Bender;
import Robots.Robot;
import Robots.Sonny;
import Robots.Spirit;
import Talos.Laberinto;

/**
 * Clase creada para ser usada en la utilidad cargador
 * contiene el main del cargador. Se crea una instancia de la clase Estacion, una instancia de la clase Cargador
 * y se procesa el fichero de inicio, es decir, se leen todas las l√≠neas y se van creando todas las instancias de la simulación
 * 
 * @version 1.0 -  02/11/2011 
 * @author Profesores DP
 */
public class Cargador {
	/**  
	número de elementos distintos que tendrá la simulación - lab, Bender, Sonny, Spirit, Asimo
	*/
	static final int NUMELTOSCONF  = 6;
	/**  
	atributo para almacenar el mapeo de los distintos elementos
	*/
	static private DatoMapeo [] mapeo;
	/**  
	referencia a la instancia del patrón Singleton
	*/
	private Laberinto lab;
	
	/** Ancho del laberinto*/
	private int ancho;

	/** Alto del laberinto*/
	private int alto;
	
	/**
	 *  constructor parametrizado 
	 *  @param e referencia a la instancia del patrón Singleton
	 */
	public Cargador() {
		mapeo = new DatoMapeo[NUMELTOSCONF];
		mapeo[0]= new DatoMapeo("LABERINTO", 5);
		mapeo[1]= new DatoMapeo("BENDER", 4);
		mapeo[2]= new DatoMapeo("SONNY", 4);
		mapeo[3]= new DatoMapeo("SPIRIT", 4);
		mapeo[4]= new DatoMapeo("ASIMO", 4);
		mapeo[5]= new DatoMapeo("ASIMOOESTE",4);
	}
	
	/**
	 *  busca en mapeo el elemento le√≠do del fichero inicio.txt y devuelve la posición en la que está 
	 *  @param elto elemento a buscar en el array
	 *  @return res posición en mapeo de dicho elemento
	 */
	private int queElemento(String elto)  {
	    int res=-1;
	    boolean enc=false;

	    for (int i=0; (i < NUMELTOSCONF && !enc); i++)  {
	        if (mapeo[i].getNombre().equals(elto)) {
	            res=i;
	            enc=true;
	        }
	    }
	    return res;
	}
	
	/**
	 *  m√©todo que crea las distintas instancias de la simulación 
	 *  @param elto nombre de la instancia que se pretende crear
	 *  @param numCampos número de atributos que tendrá la instancia
	 *  @param vCampos array que contiene los valores de cada atributo de la instancia
	 */
	public void crear(String elto, int numCampos, List<String> vCampos)	{
	    //Si existe elemento y el número de campos es correcto, procesarlo... si no, error

	    //Comprobación de datos básicos correctos
	       //procesar
	       switch(queElemento(elto)) {
	        case 0:	   
	        	try {
	        		crearLab(numCampos,vCampos);
	        	} catch (CargadorException cargador_exception) {
	        		System.err.println(cargador_exception.getLaberintoMessage());
	        	}
	        	break;
	        case 1:
	        	try {
	        		crearBender(numCampos,vCampos);
	        	} catch (CargadorException cargador_exception) {
	        		System.err.println(cargador_exception.getRobotMessage());
	        	}
	        	break;
	        case 2:
	        	try {
	        		crearSonny(numCampos,vCampos);
	        	} catch (CargadorException cargador_exception) {
	        		System.err.println(cargador_exception.getRobotMessage());
	        	}
	        	break;
	        case 3:
	        	try {
	        		crearSpirit(numCampos,vCampos);
	        	} catch (CargadorException cargador_exception) {
	        		System.err.println(cargador_exception.getRobotMessage());
	        	}
	        	break;
	        case 4:
	        	try {
	        		crearAsimo(numCampos,vCampos);
	        	} catch (CargadorException cargador_exception) {
	        		System.err.println(cargador_exception.getRobotMessage());
	        	}
	        	break;
	        case 5:
	        	try{
	        		crearAsimoOeste(numCampos,vCampos);
	        	}catch(CargadorException cargador_exception){
	        		System.err.println(cargador_exception.getRobotMessage());
	        	}
	        	break;
	       }
	}

	/**
	 *  m√©todo que crea una instancia de la clase Laberinto
	 *  @param numCampos número de atributos que tendrá la instancia
	 *  @param vCampos array que contiene los valores de cada atributo
	 */
	private void crearLab(int numCampos, List<String> vCampos) throws CargadorException{
	    if(numCampos == 5){
	    	ancho = Integer.valueOf(vCampos.get(1));
	    	alto = Integer.valueOf(vCampos.get(2));
	    	lab = Laberinto.getInstancia();
	    	try {
				lab.configurarLaberinto(Integer.valueOf(vCampos.get(3)), ancho, alto,
						Integer.valueOf(vCampos.get(4)));
			} catch (LaberintoException laberinto_exception) {
				laberinto_exception.getConfiguracionMessage();
			} catch (NumberFormatException e) {
				System.err.println(e.getMessage());
			}
	    }
	    else
	    	throw new CargadorException("Cargador: Laberinto numero de campos "
	    			+ "establecidos " + numCampos);
	}
	
	/**
	 *  m√©todo que crea una instancia de la clase Bender
	 *  @param numCampos número de atributos que tendrá la instancia
	 *  @param vCampos array que contiene los valores de cada atributo
	 */
	private void crearBender(int numCampos, List<String> vCampos)throws CargadorException{
		if(numCampos == 4){
			Robot robot;
			try {
				robot = new Bender(vCampos.get(1),vCampos.get(2).charAt(0),
						Integer.valueOf(vCampos.get(3)), 0);
				lab.meterRobot(robot);
			} catch (RobotException robot_excepcion){
				System.err.println(robot_excepcion.getConfiguracionMessage());
			}
		}
		else
			throw new CargadorException("Cargador: Bender numero de campos "
					+ "establecidos " + numCampos);
	}
	
	/**
	 *  m√©todo que crea una instancia de la clase Sonny
	 *  @param numCampos número de atributos que tendrá la instancia
	 *  @param vCampos array que contiene los valores de cada atributo
	 * @throws CargadorException 
	 */
	private void crearSonny(int numCampos, List<String> vCampos) throws CargadorException{
		if(numCampos == 4){
			Robot robot;
			try {
				robot = new Sonny(vCampos.get(1),vCampos.get(2).charAt(0),
						Integer.valueOf(vCampos.get(3)), 0);
				lab.meterRobot(robot);
			} catch (RobotException robot_excepcion){
				System.err.println(robot_excepcion.getConfiguracionMessage());
			}
		}
		else
			throw new CargadorException("Cargador: Sonny numero de campos "
					+ "establecidos " + numCampos);
	}	
	
	/**
	 *  m√©todo que crea una instancia de la clase Spirit
	 *  @param numCampos número de atributos que tendrá la instancia
	 *  @param vCampos array que contiene los valores de cada atributo
	 * @throws CargadorException 
	 */
	private void crearSpirit(int numCampos, List<String> vCampos) throws CargadorException{
		if(numCampos == 4){
			Robot robot;
			try {
				robot = new Spirit(vCampos.get(1),vCampos.get(2).charAt(0),
						Integer.valueOf(vCampos.get(3)), ancho*(alto-1));
				lab.meterRobot(robot);
			} catch (RobotException robot_excepcion){
				System.err.println(robot_excepcion.getConfiguracionMessage());
			}
		}
		else
			throw new CargadorException("Cargador: Spirit numero de campos "
					+ "establecidos " + numCampos);
	}
	
	/**
	 *  m√©todo que crea una instancia de la clase Asimo
	 *  @param numCampos número de atributos que tendrá la instancia
	 *  @param vCampos array que contiene los valores de cada atributo
	 * @throws CargadorException 
	 */
	private void crearAsimo(int numCampos, List<String> vCampos) throws CargadorException{
		if(numCampos == 4){
			Robot robot;
			try {
				robot = new Asimo(vCampos.get(1),vCampos.get(2).charAt(0),
						Integer.valueOf(vCampos.get(3)), (ancho*alto)-1);
				lab.meterRobot(robot);
			} catch (RobotException robot_excepcion){
				System.err.println(robot_excepcion.getConfiguracionMessage());
			}
		}
		else
			throw new CargadorException("Cargador: Asimo numero de campos "
					+ "establecidos " + numCampos);
	}
	
	private void crearAsimoOeste(int numCampos, List<String> vCampos) throws CargadorException{
		if(numCampos == 4){
			Robot robot;
			try {
				robot = new AsimoOeste(vCampos.get(1),vCampos.get(2).charAt(0),
						Integer.valueOf(vCampos.get(3)), (ancho*alto)-1);
				lab.meterRobot(robot);
			} catch (RobotException robot_excepcion){
				System.err.println(robot_excepcion.getConfiguracionMessage());
			}
		}
		else
			throw new CargadorException("Cargador: Asimo numero de campos "
					+ "establecidos " + numCampos);
	}

}