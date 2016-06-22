package TestJUnit;

import static org.junit.Assert.*;

import org.junit.Test;

import Talos.Llave;

public class LlaveTest {

	@Test
	public void test() {
		Llave l = new Llave();
		Llave l1 = new Llave(5);
		
		assertTrue(l.getId() == 0);
		assertTrue(l1.getId() == 5);
		
		l.setId(l1.getId());
		assertFalse(l.getId() == 0);
		
		assertTrue(l.equals(l1));
		assertTrue((l.compareTo(l1) == 0));
		
		assertFalse(l.toString() == Integer.toString(0));
	}

}
