package ar.edu.itba.sds.caja;

import java.util.Random;

public class App {	
	
	private static final double P = 0.8;
	
    public static void main( String[] args ){  
    	int N = 8;
        System.out.println("Hello World!");
        System.out.println("Driver Class Caja Module\n");
        Cajas cajas = new Cajas();
        cajas.init();
        Random rand = new Random();
        
        while(true) {
	        for(int n = 0 ; n < N ; n++) {
	    		cajas.cajas.get(n).add(0);
	    		cajas.cajas.get(n).add(10);
	    		cajas.cajas.get(n).add(20);
	    		cajas.cajas.get(n).add(30);
	    		cajas.cajas.get(n).add(40);     
	    		cajas.cajas.get(n).add(50);
	    		cajas.cajas.get(n).add(60);   
	    		cajas.cajas.get(n).add(70);      
	    		cajas.cajas.get(n).add(50);
	    		cajas.cajas.get(n).add(60);
	    		cajas.cajas.get(n).add(70);
	    		double x = rand.nextDouble();
	        	if(x > P) {
	        		cajas.cajas.get(n).add(80);
	        	}
	    	}
	        cajas.run();
        }
    }
}

