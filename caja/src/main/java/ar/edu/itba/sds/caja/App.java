package ar.edu.itba.sds.caja;

import java.io.FileWriter;
import java.io.IOException;

import ar.edu.itba.sds.model.Position;

public class App {
	
    public static void main( String[] args ){
    	
        System.out.println("Hello World!");
        System.out.println("Driver Class Caja Module\n");
        
        Position p = new Position(0,0);
        CajaImpl caja = new CajaImpl(p,10,5,1,4);
        System.out.println(caja);
        
        while(true) {
        
	        caja.add(0);
	
	        caja.add(10);
	
	        caja.add(20);
	
	        caja.add(30);
	
	        caja.add(40);
	        
	        caja.add(50);
	        
	        caja.add(60);
	        
	        
	        caja.print();
	        while(!caja.isDone()) {
	        	caja.atender();
	        }
	        
	        System.out.println(caja.ovito);
	        write("output.txt",caja.ovito.toString());
	        
       }
    }
    
    // write to a file
    static void write (String filename , String value) {
     	try {
   	      FileWriter myWriter = new FileWriter(filename);
   	      myWriter.write(value);
   	      myWriter.close();
   	    } catch (IOException e) {
   	      System.out.println("An error occurred.");
   	      e.printStackTrace();
   	    }
     }
    
}
