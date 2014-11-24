package TestJUnit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Collections;
import java.util.LinkedList;

import org.junit.Test;

import Robots.Asimo;
import Robots.Bender;
import Robots.Robot;
import Robots.Sonny;
import Talos.Llave;
import Talos.Sala;

public class SalaTest {
	
	@Test
	public void testLlavesSala() {
		LinkedList<Llave> Llaves = new LinkedList<Llave> ();
		Collections.addAll(Llaves, new Llave(0), new Llave(1), new Llave(1), new Llave(2), new Llave(3));
		Sala s = new Sala(0, Llaves);
		Sala s2 = new Sala(1);
		Llave l = new Llave();
		
		assertTrue(s2.sacarLlave() == null);
		l.setId(0);
		assertTrue(s.sacarLlave().equals(l));
		l.setId(9);
		assertFalse(s.sacarLlave().equals(l));
		s.meterLlave(l);
		assertFalse(s.sacarLlave().equals(l));
		Llave l2 = new Llave (2);
		assertTrue(s.sacarLlave().equals(l2));
	}
	
	@Test
	public void testPuertaSala() {
		Sala s = new Sala(1);	
		int[] combinacion = {1,3,5,7,9,11,13,15,17,19,21,23,25,27,29};
		
		assertTrue(s.obtenerPuerta() == null);
		
		s.configurarPuerta(combinacion, 4);
		assertTrue(s.obtenerPuerta() != null);
	}
	
	@Test
	public void testRobotsSala() {
		Sala s = new Sala(1);	
		assertTrue(s.sacarRobot() == null);
		Robot R = new Bender();
		Robot R2 = new Asimo();
		Robot R3 = new Sonny();
		
		s.meterRobot(R);
		s.meterRobot(R2);
		s.meterRobot(R3);
		
		assertTrue(s.sacarRobot() == R);
		assertFalse(s.sacarRobot() == R3);
		assertTrue(s.sacarRobot() == R3);
	}

	@Test
	public void test() {
	}

}
