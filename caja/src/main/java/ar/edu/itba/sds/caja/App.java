package ar.edu.itba.sds.caja;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ar.edu.itba.sds.model.Agent;

public class App {	
	
	private static final double P = 0.5;
	
    public static void main( String[] args ){  
    	int N = 8;
        System.out.println("Hello World!");
        System.out.println("Driver Class Caja Module\n");
        Cajas cajas = new Cajas();
        //cajas.init();
        //Cajas cajas = Cajas.getInstance();
        Random rand = new Random();
        
        //------------------------------------//
        int AGENTS = 100;
        Agent agent = new Agent();
        List<Agent> agents = new ArrayList<>();
        for(int i = 0 ; i < AGENTS ; i++) {
        	Agent aux = new Agent();
        	aux.id = i;
        	agents.add(aux);
        }
        //------------------------------------//
        
        while(true) {
        	int cons = 0;
	        for(int n = 0 ; n < N ; n++) {
	    		cajas.add(0,n,agents.get(cons));
	    		cons++;
	    		cajas.add(10,n,agents.get(cons));
	    		cons++;
	    		cajas.add(20,n,agents.get(cons));
	    		cons++;
	    		cajas.add(30,n,agents.get(cons));
	    		cons++;
	    		cajas.add(40,n,agents.get(cons)); 
	    		cons++;
	    		double x = rand.nextDouble();
	        	if(x > P) {
	        		//cajas.add(80,n);
	        		int index = cajas.whereToGo();
	        		System.out.println("Agrego en la caja " + index);
	        		cajas.add(80,index,agents.get(cons));
	        		cons++;
	        	}
	    	}
	        /*
	        Llamada a whereToGo ?
	        */
	        cajas.whereToGo();
	        String status = cajas.status();
	        System.out.println(status);
	        cajas.run();
        }
    }
    public static int getRandomNumberInRange(int min, int max) {
		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}
		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
}

