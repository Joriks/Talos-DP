package Cargador;

import java.util.List;

import Excepciones.RobotException;
import Robots.Asimo;
import Robots.Bender;
import Robots.Direccion;
import Robots.Robot;
import Robots.Sonny;
import Robots.Spirit;
import Talos.Laberinto;

/**
 * Clase creada para ser usada en la utilidad cargador
 * contiene el main del cargador. Se crea una instancia de la clase Estacion, una instancia de la clase Cargador
 * y se procesa el fichero de inicio, es decir, se leen todas las lÃ­neas y se van creando todas las instancias de la simulaci—n
 * 
 * @version 1.0 -  02/11/2011 
 * @author Profesores DP
 */
public class Cargador {
	/**  
	nœmero de elementos distintos que tendr‡ la simulaci—n - lab, Bender, Sonny, Spirit, Asimo
	*/
	static final int NUMELTOSCONF  = 5;
	/**  
	atributo para almacenar el mapeo de los distintos elementos
	*/
	static private DatoMapeo [] mapeo;
	/**  
	referencia a la instancia del patr—n Singleton
	*/
	private Laberinto lab;
	
	/** Ancho del laberinto*/
	private int ancho;

	/** Alto del laberinto*/
	private int alto;
	
	/**
	 *  constructor parametrizado 
	 *  @param e referencia a la instancia del patr—n Singleton
	 */
	public Cargador() {
		mapeo = new DatoMapeo[NUMELTOSCONF];
		mapeo[0]= new DatoMapeo("LABERINTO", 5);
		mapeo[1]= new DatoMapeo("BENDER", 4);
		mapeo[2]= new DatoMapeo("SONNY", 4);
		mapeo[3]= new DatoMapeo("SPIRIT", 4);
		mapeo[4]= new DatoMapeo("ASIMO", 4);
	}
	
	/**
	 *  busca en mapeo el elemento leÃ­do del fichero inicio.txt y devuelve la posici—n en la que est‡ 
	 *  @param elto elemento a buscar en el array
	 *  @return res posici—n en mapeo de dicho elemento
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
	 *  mÃ©todo que crea las distintas instancias de la simulaci—n 
	 *  @param elto nombre de la instancia que se pretende crear
	 *  @param numCampos nœmero de atributos que tendr‡ la instancia
	 *  @param vCampos array que contiene los valores de cada atributo de la instancia
	 */
	public void crear(String elto, int numCampos, List<String> vCampos)	{
	    //Si existe elemento y el nœmero de campos es correcto, procesarlo... si no, error
	    int numElto = queElemento(elto);

	    //Comprobaci—n de datos b‡sicos correctos
	    if ((numElto!=-1) && (mapeo[numElto].getCampos() == numCampos))   {
	       //procesar
	       switch(queElemento(elto)) {
	        case 0:	   
	            crearLab(numCampos,vCampos);
	            break;
	        case 1:
	            crearBender(numCampos,vCampos);
	            break;
	        case 2:
	            crearSonny(numCampos,vCampos);
	            break;
	        case 3:
	            crearSpirit(numCampos,vCampos);
	            break;
	        case 4:
	            crearAsimo(numCampos,vCampos);
	            break;
	     	}
	    }
	    else
	        System.out.println("ERROR Cargador::crear: Datos de configuraci—n "
	        		+ "incorrectos... " + elto + "," + numCampos+"\n");
	}

	/**
	 *  mÃ©todo que crea una instancia de la clase Planta
	 *  @param numCampos nœmero de atributos que tendr‡ la instancia
	 *  @param vCampos array que contiene los valores de cada atributo
	 */
	private void crearLab(int numCampos, List<String> vCampos){
	    if(numCampos == 5){
	    	ancho = Integer.valueOf(vCampos.get(1));
	    	alto = Integer.valueOf(vCampos.get(2));
	    	lab = Laberinto.getInstancia();
	    	lab.configurarLaberinto(Integer.valueOf(vCampos.get(3)), ancho, alto,
	    			Integer.valueOf(vCampos.get(4)));
//	    	System.out.println("Creado laberinto: Ancho:" + ancho + 
//	    			" Alto:" + alto + " Salida:" + vCampos.get(3) + 
//	    			" Altura:" + vCampos.get(4) +"\n");
	    }
	    else
	    	System.err.println("Error en el número de campos del laberinto");
	}
	
	/**
	 *  mÃ©todo que crea una instancia de la clase Bender
	 *  @param numCampos nœmero de atributos que tendr‡ la instancia
	 *  @param vCampos array que contiene los valores de cada atributo
	 */
	private void crearBender(int numCampos, List<String> vCampos){
		if(numCampos == 4){
			Robot robot;
			try {
				robot = new Bender(vCampos.get(1),vCampos.get(2).charAt(0),
						Integer.valueOf(vCampos.get(3)), 0);
				lab.meterRobot(robot);
			} catch (RobotException e){
				e.configuracionMal(vCampos, 0);
			}

//		    System.out.println("Creado Bender: " + robot.toString() + "\n");
		}
		else
			System.err.println("Error en el número de campos del robot Bender");
	}
	
	/**
	 *  mÃ©todo que crea una instancia de la clase Sonny
	 *  @param numCampos nœmero de atributos que tendr‡ la instancia
	 *  @param vCampos array que contiene los valores de cada atributo
	 */
	private void crearSonny(int numCampos, List<String> vCampos){
		if(numCampos == 4){
			Robot robot;
			try {
				robot = new Sonny(vCampos.get(1),vCampos.get(2).charAt(0),
						Integer.valueOf(vCampos.get(3)), 0);
				lab.meterRobot(robot);
			} catch (RobotException e){
				e.configuracionMal(vCampos, 0);
			}
			
//		    System.out.println("Creado Sonny: " + robot.toString() + "\n");
		}
		else
			System.err.println("Error en el número de campos del robot Sonny");
	}	
	
	/**
	 *  mÃ©todo que crea una instancia de la clase Spirit
	 *  @param numCampos nœmero de atributos que tendr‡ la instancia
	 *  @param vCampos array que contiene los valores de cada atributo
	 */
	private void crearSpirit(int numCampos, List<String> vCampos){
		if(numCampos == 4){
			Robot robot;
			try {
				robot = new Spirit(vCampos.get(1),vCampos.get(2).charAt(0),
						Integer.valueOf(vCampos.get(3)), ancho*(alto-1));
				lab.meterRobot(robot);
			} catch (RobotException e){
				e.configuracionMal(vCampos, ancho*(alto-1));
			}
			
//		    System.out.println("Creado Spirit: " + robot.toString() + "\n");
		}
		else
			System.err.println("Error en el número de campos del robot Spirit");
	}
	
	/**
	 *  mÃ©todo que crea una instancia de la clase Asimo
	 *  @param numCampos nœmero de atributos que tendr‡ la instancia
	 *  @param vCampos array que contiene los valores de cada atributo
	 */
	private void crearAsimo(int numCampos, List<String> vCampos){
		if(numCampos == 4){
			Robot robot;
			try {
				robot = new Asimo(vCampos.get(1),vCampos.get(2).charAt(0),
						Integer.valueOf(vCampos.get(3)), (ancho*alto)-1);
				lab.meterRobot(robot);
			} catch (RobotException e){
				e.configuracionMal(vCampos, (ancho*alto)-1);
			}
			
//		    System.out.println("Creado Asimo: " + robot.toString() + "\n");
		}
		else
			System.err.println("Error en el número de campos del robot Asimo");
	}
}