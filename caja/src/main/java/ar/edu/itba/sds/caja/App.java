package ar.edu.itba.sds.caja;

import java.util.Random;

public class App {	
	
	private static final double P = 0.5;
	
    public static void main( String[] args ){  
    	int N = 8;
        System.out.println("Hello World!");
        System.out.println("Driver Class Caja Module\n");
        Cajas cajas = new Cajas();
        cajas.init();
        Random rand = new Random();
        
        while(true) {
	        for(int n = 0 ; n < N ; n++) {
	    		cajas.add(0,n);
	    		cajas.add(10,n);
	    		cajas.add(20,n);
	    		cajas.add(30,n);
	    		cajas.add(40,n);     
	    		cajas.add(50,n);
	    		cajas.add(60,n);   
	    		cajas.add(70,n);      
	    		cajas.add(50,n);
	    		cajas.add(60,n);
	    		cajas.add(70,n);
	    		double x = rand.nextDouble();
	        	if(x > P) {
	        		cajas.add(80,n);
	        	}
	    	}
	        cajas.run();
        }
    }
}

