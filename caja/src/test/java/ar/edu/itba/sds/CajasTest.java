package ar.edu.itba.sds;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ar.edu.itba.sds.caja.Cajas;
import ar.edu.itba.sds.model.Agent;

public class CajasTest {
	/*
	Tiene parámetros por default
	
	*/
	Cajas cajas = new Cajas();
	
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
		//cajas.add(elem, index, agent);// ----> It would be just cleaner if we just do cajas.add(agent);
		Agent agent = new Agent();
		agent.id = 9;
		cajas.add(10,3,agent);
		status();
	}
	
	//@Test
	public void addTest() {
		
	}
	
	//@Test 
	public void getPositionOfTest() {
		//status();
		//cajas.add(elem, index, agent);// ----> It would be just cleaner if we just do cajas.add(agent);
		cajas.add(10,0,agents[0]);
		cajas.add(10,0,agents[1]);
		cajas.add(10,0,agents[2]);
		cajas.add(10,0,agents[3]);
		cajas.add(10,0,agents[4]);
		cajas.add(10,0,agents[5]);
		cajas.add(10,0,agents[6]);
		cajas.add(10,0,agents[7]);
		cajas.add(10,0,agents[8]);
		status();
		System.out.println(cajas.getPositionOf(0));
		System.out.println(cajas.getPositionOf(1));
		System.out.println(cajas.getPositionOf(2));
		System.out.println(cajas.getPositionOf(3));
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
	
	private void status() {
		String str = cajas.status();
		System.out.println(str);
	}
	
}
