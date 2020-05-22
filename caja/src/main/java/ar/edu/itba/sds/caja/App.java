package ar.edu.itba.sds.caja;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ar.edu.itba.sds.interfaces.Caja;
import ar.edu.itba.sds.model.Position;

public class App {
	
	private static final double P = 0.7;
	private static final int I = 10000000;
	
    public static void main( String[] args ){
		File f = new File("output.txt");
		f.delete();
    	
        System.out.println("Hello World!");
        System.out.println("Driver Class Caja Module\n");
        
        Position p = new Position(0,0);
        CajaImpl caja = new CajaImpl(p,7.5,5,1.5,8);
        System.out.println(caja);
        
        boolean flag = false;
        
        int N = 8;
        double delta = 20;
        
        List<CajaImpl> cajas = new ArrayList(N);
        for(int n = 0 ; n < N ; n++) {
        	Position pn = new Position(n*delta,0);
        	CajaImpl cajan = new CajaImpl(pn,7.5,5,1.5,8);
        	cajas.add(cajan);
            System.out.println(cajan);
        }
        
        Random rand = new Random();
        
        int i = 0;
        
        while(true) {
        	
        	for(int n = 0 ; n < N ; n++) {
        		cajas.get(n).add(0);
        		
    	        cajas.get(n).add(10);
    	
    	        cajas.get(n).add(20);
    	
    	        cajas.get(n).add(30);
    	
    	        cajas.get(n).add(40);
    	        
    	        cajas.get(n).add(50);
    	        
    	        cajas.get(n).add(60);
    	        
    	        cajas.get(n).add(70);
    	        
    	        cajas.get(n).add(50);
    	        
    	        cajas.get(n).add(60);
    	        
    	        cajas.get(n).add(70);
        	}
        	
	        for(int m = 0 ; m < N ; m++) {
		        while(!cajas.get(m).isDone()/*TODO: revisar esta condicion ej: dedicarle 30s a cada caja | hacer fork  */) {
		        	i++;
		        	if(i%1000000==0) {
		        		flag = true;
		        	}
		        	for(int n = 0 ; n < N ; n++) {
			        	double x = rand.nextDouble();
			        	if(flag){
			        		cajas.get(n).printOvito();
			        	}
			        	/*if(x > P && i%I==0) {
			        		cajas.get(n).add(80);
			        	}*/
			        	cajas.get(n).atender();
		        	}
		        	if(flag) {
		        		flag = false;
		        	}
			        	
		        }
	        }
	        
	        for(int n = 0 ; n < N ; n++) {
		        System.out.println(cajas.get(n).counterA + "	" + cajas.get(n).counterB);
		        
		        write("output" + n + ".txt",cajas.get(n).ovito.toString());
	        }
	        
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
     
    public static String poligons(){
    	StringBuilder sb = new StringBuilder();
		 for (int i = 5; i < 10 ; i++) {
			 for (int j = 5; j <20; j++) {
				 sb.append(-3*i*j + " " + i + " " + j + " " + 1  + " " +  1 + " " + 1 + " " + 1 + "\n" );
			 }
		 }
		 for (int i = 5; i < 10 ; i++) {
			 for (int j = 5; j <20; j++) {
				 sb.append(-7*i*j + " " + -i + " " + j + " " + 1  + " " +  1 + " " + 1 + " " + 1 + "\n" );
			 }
		 }

		 return sb.toString();
	 }

	public static String poligons(double xi, double xf, double yi, double yf ){
		StringBuilder sb = new StringBuilder();
		for (int i = 5; i < 10 ; i++) {
			for (int j = 5; j <20; j++) {
				sb.append(-3*i*j + " " + i + " " + j + " " + 1  + " " +  1 + " " + 1 + " " + 1 + "\n" );
			}
		}
		for (int i = 5; i < 10 ; i++) {
			for (int j = 5; j <20; j++) {
				sb.append(-7*i*j + " " + -i + " " + j + " " + 1  + " " +  1 + " " + 1 + " " + 1 + "\n" );
			}
		}

		return sb.toString();
	}
    
}
