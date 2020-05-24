package ar.edu.itba.sds.caja;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ar.edu.itba.sds.interfaces.Caja;
import ar.edu.itba.sds.model.Position;

public class Cajas implements Caja {
	
	
	private static final int N = 8;
    private static final double delta = 20;
    
    int i = 0;
    
    boolean flag = false;
    StringBuilder mergedStr = new StringBuilder("");
    List<CajaImpl> cajas = new ArrayList(N);
    
    
    public void init() {
	    for(int n = 0 ; n < N ; n++) {
	    	Position pn = new Position(n*delta,0);
	    	CajaImpl cajan = new CajaImpl(pn,7.5,5,1.5,8);
	    	cajas.add(cajan);
	    }
    }
    
    public void run() {
        for(int m = 0 ; m < N ; m++) {
	        while(!cajas.get(m).isDone()) {
	        	i++;
	        	if(i%100000==0) {
	        		flag = true;
	        	}
	        	int size = 0;
	        	StringBuilder str = new StringBuilder();
	        	for(int n = 0 ; n < N ; n++) {			        	
		        	if(flag){
		        		cajas.get(n).printOvito();
		        		size = size + cajas.get(n).queue.size() + 2;
		        		str.append(cajas.get(n).toOvitoBody());
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
   
	@Override
	public Position position(int index) {
		Position p = new Position(cajas.get(index).position(index).x , cajas.get(index).position(index).y + index*cajas.get(index).d + cajas.get(index).H);
		return p;
	}
	
	@Override
	public boolean hasFreeSpace(int index) {
		return cajas.get(index).queue.size() < N;
	}
	
	@Override
	public List<Integer> status() {
		return null;
	}
	
//	@Override
//	public String toString() {
//		return String.format("p = %s		L = %s	R = %s	\n",p.toString(),L.toString(),R.toString());
//	}
	
	@Override
	public void add(int elem , int index) {
		if(cajas.get(index).queue.size() < N)
			cajas.get(index).queue.add(elem);
	}
	
	@Override
	public int whereToGo() {
		return 0;
	}
}
