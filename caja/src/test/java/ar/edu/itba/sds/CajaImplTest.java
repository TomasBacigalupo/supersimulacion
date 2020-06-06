package ar.edu.itba.sds;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ar.edu.itba.sds.caja.CajaImpl;
import ar.edu.itba.sds.model.Agent;
import ar.edu.itba.sds.model.Vector;

public class CajaImplTest {
	/*
	Tiene parámetros por default!
	*/
	CajaImpl cajas = new CajaImpl();
	
	private int N = 10;
	
	Agent[] agents = new Agent[N];
	
	@Before
	public void setUp() {
		for(int i = 0 ; i < N ; i++) {
			Agent aux = new Agent();
			aux.id = i;
			agents[i] = aux;
		}
	}
	
	//@Test
	public void statusTest() {
		status();
		Agent agent = new Agent();
		agent.id = 9;
		cajas.add(10,3,agent);
		status();
	}
	
	@Test
	public void addTest() {
		cajas.add(10,0,agents[0]);
		assertTrue(0==cajas.getPositionOf(0).x);
		assertTrue(5==cajas.getPositionOf(0).y);
	}
	
	@Test
	public void addWhenItIsFullTest() {
		cajas.add(10,0,agents[0]);
		cajas.add(10,0,agents[1]);
		cajas.add(10,0,agents[2]);
		cajas.add(10,0,agents[3]);
		cajas.add(10,0,agents[4]);
		cajas.add(10,0,agents[5]);
		cajas.add(10,0,agents[6]);
		cajas.add(10,0,agents[7]);
		cajas.add(10,0,agents[8]);
		assertNull(cajas.getPositionOf(8));
	}
	
	//@Test 
	public void getPositionOfTest() {
		//status();
		cajas.add(10,0,agents[0]);
		cajas.add(10,0,agents[1]);
		cajas.add(10,0,agents[2]);
		cajas.add(10,1,agents[3]);
		cajas.add(10,1,agents[4]);
		cajas.add(10,1,agents[5]);
		cajas.add(10,2,agents[6]);
		cajas.add(10,2,agents[7]);
		cajas.add(10,3,agents[8]);
		status();
		System.out.println(cajas.getPositionOf(0));
		assertTrue(0==cajas.getPositionOf(0).x);
		assertTrue(5==cajas.getPositionOf(0).y);
		System.out.println(cajas.getPositionOf(1));
		assertTrue(0==cajas.getPositionOf(1).x);
		assertTrue(7==cajas.getPositionOf(1).y);
		System.out.println(cajas.getPositionOf(2));
		assertTrue(0==cajas.getPositionOf(2).x);
		assertTrue(9==cajas.getPositionOf(2).y);
		System.out.println(cajas.getPositionOf(3));
		assertTrue(20==cajas.getPositionOf(3).x);
		assertTrue(5==cajas.getPositionOf(3).y);
		System.out.println(cajas.getPositionOf(4));
		System.out.println(cajas.getPositionOf(5));
		System.out.println(cajas.getPositionOf(6));
		System.out.println(cajas.getPositionOf(7));
		System.out.println(cajas.getPositionOf(8));
	}
	
	@Test
	public void getPositionOfNotFoundTest() {
		cajas.add(10,5,agents[8]);
		//status();
		//System.out.println(cajas.getPositionOf(0));
		assertNull(cajas.getPositionOf(0));
	}
	
	@Test
	public void whereToGo() {
		cajas.add(10,0,agents[0]);
		cajas.add(10,2,agents[1]);
		//status();
		//System.out.println(cajas.whereToGo());
		assertEquals(1,cajas.whereToGo());
	}
	
	@Test
	public void positionTest() {
		//status();
		cajas.add(10, 5, agents[0]);
		//status();
		Vector pos = cajas.position(5);
		//System.out.println(pos);
	}
	
	@Test
	public void atendidosTest() {
		cajas.add(10, 0, agents[9]);
		cajas.add(10, 0, agents[9]);
		cajas.add(10, 0, agents[0]);
		cajas.add(10, 0, agents[1]);
		status();
		cajas.run();
		assertEquals(4,cajas.atendidos());
	}
	
	private void status() {
		String str = cajas.status();
		System.out.println(str);
	}
	
}
