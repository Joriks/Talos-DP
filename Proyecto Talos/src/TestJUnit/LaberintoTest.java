package TestJUnit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import Excepciones.RobotException;
import Robots.Asimo;
import Robots.Robot;
import Talos.Laberinto;
import Talos.Sala;

public class LaberintoTest {

	@Test
	public void testPuerta() {
		Laberinto l = Laberinto.getInstancia();
		l.configurarLaberinto(29, 5, 6, 3);
		int[] combinacion = {1,3,5,7,9,11,13,15,17,19,21,23,25,27,29};
		
		assertFalse(l.tienePuerta(29));
		
		l.configurarPuerta(combinacion);
		assertTrue(l.tienePuerta(29));
		
		assertFalse(l.tienePuerta(1));
		assertFalse(l.tienePuerta(27));
	}
	
	@Test
	public void testLlaves() {
		Laberinto l = Laberinto.getInstancia();
		l.configurarLaberinto(29, 5, 6, 3);
		Sala s = new Sala(0);
		
		s = l.obtenerSala(0);
		assertTrue(s.sacarLlave() == null);
		s = l.obtenerSala(3);
		assertTrue(s.sacarLlave() != null);
		s = l.obtenerSala(7);
		assertTrue(s.sacarLlave() == null);
		s = l.obtenerSala(8);
		assertFalse(s.sacarLlave() == null);
	}
	
	@Test
	public void testRobots() {
		Laberinto l = Laberinto.getInstancia();
		l.configurarLaberinto(29, 5, 6, 3);
		Robot a;
		try {
			a = new Asimo("Asimo1", 'A', 0, 0);
			Robot a2 = new Asimo("Asimo2", 'A', 0, 0);
			Sala s = new Sala(0);
			Robot r = new Asimo();
			
			s = l.obtenerSala(0);
			r = s.sacarRobot();
			assertTrue(r == null);
			
			l.meterRobot(a);
			l.meterRobot(a2);
			s = l.obtenerSala(0);
			r = s.sacarRobot();
			assertTrue(r != null);
			
			s = l.obtenerSala(0);
			r = s.sacarRobot();
			assertTrue(r != null);
			
			s = l.obtenerSala(0);
			r = s.sacarRobot();
			assertTrue(r == null);
		} catch (RobotException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test() {
	}

}
