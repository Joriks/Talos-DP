package TestJUnit;

import static org.junit.Assert.*;

import org.junit.Test;

import Robots.Asimo;
import Robots.Bender;
import Robots.Robot;
import Robots.Sonny;
import Talos.Laberinto;
import Talos.Llave;
import Talos.Sala;

public class LaberintoTest {

	@Test
	public void testPuerta() {
		Laberinto l = new Laberinto(29,5,6,3);
		int[] combinacion = {1,3,5,7,9,11,13,15,17,19,21,23,25,27,29};
		
		assertFalse(l.tienePuerta(29));
		
		l.configurarPuerta(combinacion);
		assertTrue(l.tienePuerta(29));
		
		assertFalse(l.tienePuerta(1));
		assertFalse(l.tienePuerta(27));
	}
	
	@Test
	public void testLlaves() {
		Laberinto l = new Laberinto(29,5,6,3);
		int[] id_salas_llaves = {3,4,6,8,9,10,11,12,13};
		int[] llaves_sala = {0,1,1,2,3,3,4,5,5,6,7,7,8,9,9,10,11,11,12,13,13,14
				,15,15,16,17,17,18,19,19,20,21,21,22,23,23,24,25,25,26,27,27,28
				,29,29};
		Sala s = new Sala(0);
		
		l.distribuirLlaves(id_salas_llaves, llaves_sala);
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
		Laberinto l = new Laberinto(29,5,6,3);
		Robot a = new Asimo('A', 0, 0);
		Robot a2 = new Asimo('A', 0, 0);
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
		
	}
	
	@Test
	public void test() {
		testPuerta();
		testLlaves();
		testRobots();
	}

}
