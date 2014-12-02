package TestJUnit;

import static org.junit.Assert.*;

import org.junit.Test;

import Talos.Estados;
import Talos.Llave;
import Talos.Puerta;

public class PuertaTest {

	@Test
	public void estadoPuerta(){
		Puerta p1 = new Puerta();
		int[] combi1 = {1,2,3,4,5};
		
		assertTrue(p1.estadoPuerta() == Estados.NoConfigurada);
		p1.configurarPuerta(combi1, 5);
		assertTrue(p1.estadoPuerta() == Estados.Cerrada);
		p1.probarLlave(new Llave(1));
		assertTrue(p1.estadoPuerta() == Estados.Abierta);
	}
	
	@Test
	public void configurarPuerta(){
		Puerta p1 = new Puerta();
		int[] combi1 = {1,2,3,4,5};
		
		p1.configurarPuerta(combi1, 5);
		assertTrue(p1.estadoPuerta() == Estados.Cerrada);
	}
	
	@Test
	public void test() {
		Puerta p1 = new Puerta();
		int[] combi1 = {1,2,3,4,5};
		int[] combi2 = {1,5,6,8,9,10};
		Puerta p2 = new Puerta(combi2, 3);
		
		assertTrue(p1.estadoPuerta() == Estados.NoConfigurada);
		assertFalse(p2.estadoPuerta() == Estados.NoConfigurada);
		assertFalse(p2.estadoPuerta() == Estados.Abierta);
		
		p1.configurarPuerta(combi1, 3);
		assertTrue(p1.estadoPuerta() == Estados.Cerrada);
		
		p1.probarLlave(new Llave(4));
		p1.probarLlave(new Llave(2));
		p1.probarLlave(new Llave(5));
		
		assertTrue(p1.estadoPuerta() == Estados.Abierta);
		
		assertTrue(p2.probarLlave(new Llave(5)));
		assertFalse(p2.probarLlave(new Llave(2)));
		assertFalse(p2.probarLlave(new Llave(5)));
		
		assertTrue(p2.estadoPuerta() == Estados.Cerrada);
		
	}
}
