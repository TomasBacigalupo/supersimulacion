package ar.edu.itba.sds;

import org.junit.Test;

import ar.edu.itba.sds.caja.Cajas;
import ar.edu.itba.sds.model.Agent;

public class CajasTest {
	/*
	Tiene parámetros por default
	
	*/
	Cajas cajas = new Cajas();
	
	
	
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
	
	@Test 
	public void getPositionOfTest() {
		//status();
		//cajas.add(elem, index, agent);// ----> It would be just cleaner if we just do cajas.add(agent);
		Agent agent1 = new Agent();
		agent1.id = 1;
		cajas.add(10,0,agent1);
		Agent agent2 = new Agent();
		agent2.id = 2;
		cajas.add(10,0,agent2);
		status();
		System.out.println(cajas.getPositionOf(1));
		System.out.println(cajas.getPositionOf(2));
	}
	
	//@Test
	public void getPositionOfNotFoundTest() {
		
	}
	
	private void status() {
		String str = cajas.status();
		System.out.println(str);
	}
	
}
