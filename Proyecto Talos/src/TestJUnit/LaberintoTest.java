package TestJUnit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import Excepciones.LaberintoException;
import Excepciones.RobotException;
import Robots.Asimo;
import Robots.Robot;
import Talos.Laberinto;
import Talos.Sala;

public class LaberintoTest {

	@Test
	public void testPuerta() {
		Laberinto l = Laberinto.getInstancia();
		try {
			l.configurarLaberinto(29, 5, 6, 3);
		} catch (LaberintoException e) {
			e.printStackTrace();
		}
		
		assertFalse(l.tienePuerta(29));
		
		assertTrue(l.tienePuerta(29));
		
		assertFalse(l.tienePuerta(1));
		assertFalse(l.tienePuerta(27));
	}
	
	@Test
	public void testLlaves() {
		Laberinto l = Laberinto.getInstancia();
		try {
			l.configurarLaberinto(29, 5, 6, 3);
		} catch (LaberintoException e) {
			e.printStackTrace();
		}
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
		try {
			l.configurarLaberinto(29, 5, 6, 3);
		} catch (LaberintoException e1) {
			e1.printStackTrace();
		}
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
	public void testConfiguracionInicial(){
		System.out.println("Test de kruskal para laberinto de 6x6");
		Laberinto l = Laberinto.getInstancia();
		try {
			l.configurarLaberinto(35, 6, 6, 5);
			System.out.println("El algoritmo de kruskal debe tirar la pared"
					+ "entre la sala 34 y la sala 35");
		} catch (LaberintoException e) {
			e.printStackTrace();
		}
	}
		
	@Test
	public void test() {
	}

}
