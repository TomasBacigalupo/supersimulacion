package ar.edu.itba.sds.caja;

import ar.edu.itba.sds.model.Position;

public class App {
	
	
	
    public static void main( String[] args ){
    	
        System.out.println("Hello World!");
        System.out.println("Driver Class Caja Module\n");
        
        Position p = new Position(0,0);
        CajaImpl caja = new CajaImpl(p,10,5,1,5);
        System.out.println(caja);
        
        
        
        caja.add(0);

        caja.add(10);

        caja.add(20);

        caja.add(30);

        caja.add(40);
        
        caja.add(50);
        
        
        caja.print();
        while(!caja.isDone()) {
        	caja.atender();
        }
        
    }
}
