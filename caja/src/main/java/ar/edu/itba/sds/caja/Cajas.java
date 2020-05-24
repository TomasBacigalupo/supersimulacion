package ar.edu.itba.sds.caja;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ar.edu.itba.sds.model.Position;

public class Cajas {
	
	private static final double P = 0.6;
	private static final int I = 1000000;
	private static final int N = 8;
    private static final double delta = 20;
    
    public void run() {
		boolean flag = false;
	    StringBuilder mergedStr = new StringBuilder("");
	    List<CajaImpl> cajas = new ArrayList(N);
	    for(int n = 0 ; n < N ; n++) {
	    	Position pn = new Position(n*delta,0);
	    	CajaImpl cajan = new CajaImpl(pn,7.5,5,1.5,8);
	    	cajas.add(cajan);
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
		        while(!cajas.get(m).isDone()) {
		        	i++;
		        	if(i%100000==0) {
		        		flag = true;
		        	}
		        	int size = 0;
		        	StringBuilder str = new StringBuilder();
		        	for(int n = 0 ; n < N ; n++) {
			        	double x = rand.nextDouble();
			        	if(flag){
			        		cajas.get(n).printOvito();
			        		size = size + cajas.get(n).queue.size() + 2;
			        		str.append(cajas.get(n).toOvitoBody());
			        	}
			        	if(x > P && i%I==0) {
			        		cajas.get(n).add(80);
			        	}
			        	cajas.get(n).atender();
		        	}
		        	if(flag) {
		        		String appender = size + "\n" + "//\n" + str.toString();
		        		flag = false;
		        		mergedStr.append(appender);
		        	}	
		        }
	        }
	        for(int n = 0 ; n < N ; n++) {
		        System.out.println(cajas.get(n).counterA + "	" + cajas.get(n).counterB);
		        write("outputMerged.txt",mergedStr.toString());
	        }
	        
	   }
   }
   
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
