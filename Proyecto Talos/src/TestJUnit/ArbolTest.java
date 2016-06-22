package TestJUnit;

import static org.junit.Assert.*;

import org.junit.Test;

import Talos.Llave;
import Estructuras.Arbol;

public class ArbolTest {

	@Test
	public void insertar(){
		Arbol<Llave> arbol = new Arbol<Llave>();
		assertTrue(arbol.insertar(new Llave(5)));
		assertTrue(arbol.pertenece(new Llave(5)));
		assertFalse(arbol.insertar(new Llave(5)));
	}
	
	@Test
	public void borrar(){
		Arbol<Llave> arbol1 = new Arbol<Llave>();
		Arbol<Llave> arbol2;
		arbol1.insertar(new Llave(5));
		arbol1.insertar(new Llave(3));
		arbol1.insertar(new Llave(21));
		arbol2 = new Arbol<Llave>(arbol1);
		arbol1.borrar(new Llave(5));
		assertFalse(arbol1.pertenece(new Llave(5)));
		assertTrue(arbol1.pertenece(new Llave(21)));
		arbol2.insertar(new Llave(14));
		arbol2.borrar(new Llave(21));
		assertTrue(arbol2.pertenece(new Llave(5)));
		assertFalse(arbol2.pertenece(new Llave(21)));
		
	}
	
	@Test
	public void test() {
		Arbol<Llave> arbol_Llave1 = new Arbol<Llave>();
		Arbol<Llave> arbol_Llave2 = new Arbol<Llave>();
		
		assertFalse(arbol_Llave1 == arbol_Llave2);
		
		assertTrue(arbol_Llave1.vacio());
		assertFalse(!arbol_Llave2.vacio());
		
		arbol_Llave1.insertar(new Llave(5));
		arbol_Llave2.insertar(new Llave(10));
		
		Llave d1 = arbol_Llave1.getRaiz();
		Llave d2 = arbol_Llave2.getRaiz();
		assertFalse(d1.equals(d2));
		assertTrue(d1.compareTo(d2) < 0);
		
		arbol_Llave1.insertar(new Llave());
		arbol_Llave2.insertar(new Llave(4));
		
		assertTrue(arbol_Llave1.pertenece(new Llave(0)));
		assertFalse(arbol_Llave2.pertenece(new Llave(5)));
		
		arbol_Llave1.borrar(new Llave(5));
		arbol_Llave2.borrar(new Llave(4));
		assertFalse(arbol_Llave1.pertenece(new Llave(5)));
		assertFalse(arbol_Llave2.pertenece(new Llave(4)));
		
		arbol_Llave1.insertar(new Llave(1));
		arbol_Llave1.insertar(new Llave(2));
		arbol_Llave2.insertar(new Llave(3));
		arbol_Llave2.insertar(new Llave(11));
		
		assertTrue(arbol_Llave1.profundidad() == 3);
		assertFalse(arbol_Llave2.profundidad() > 2);
		
		assertFalse(arbol_Llave1.esHoja(new Llave(0)));
		assertFalse(arbol_Llave1.esHoja(new Llave(1)));
		assertTrue(arbol_Llave1.esHoja(new Llave(2)));
		assertFalse(arbol_Llave2.esHoja(new Llave(10)));
		assertTrue(arbol_Llave2.esHoja(new Llave(11)));
		
		assertTrue(arbol_Llave1.nodosHoja() == 1);
		assertTrue(arbol_Llave1.nodosInternos() == 2);
		assertTrue(arbol_Llave2.nodosHoja() == 2);
		assertFalse(arbol_Llave2.nodosInternos() > 1);
	
	}

}
