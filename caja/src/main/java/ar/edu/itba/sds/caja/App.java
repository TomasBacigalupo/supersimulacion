package ar.edu.itba.sds.caja;

import ar.edu.itba.sds.model.Position;

public class App {
	
	
	
    public static void main( String[] args ){
    	
        System.out.println("Hello World!");
        System.out.println("Driver Class Caja Module\n");
        
        Position p = new Position(0,0);
        CajaImpl caja = new CajaImpl(p,10,5,1,5);
        System.out.println(caja);
        System.out.println(caja.position(0));
        System.out.println(caja.position(1));
        System.out.println(caja.position(2));
        
        caja.add(0);

        caja.add(1);

        caja.add(2);

        caja.add(3);

        caja.add(4);
        
        while(!caja.isDone()) {
        	caja.atender();
        }
        
    }
}
