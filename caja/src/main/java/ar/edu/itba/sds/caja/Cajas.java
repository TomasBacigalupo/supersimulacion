package ar.edu.itba.sds.caja;

import java.io.FileWriter;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ar.edu.itba.sds.interfaces.Caja;
import ar.edu.itba.sds.model.Agent;
import ar.edu.itba.sds.model.Vector;

public class Cajas implements Caja {
	private int N = 8;// Cantidad de cajas
    private double delta = 20; // Separacion entre el punto p que parametriza cada caja
    private double D = 7.5;// Distancia de separación entre cajas
	private double H = 5;// Distancia social interno de la caja
	private double d = 2;// Distancia social interno de la caja
	private int max = 8;// Máxima capacidad de cada fila interno de la caja
	
    private int i = 0;
    boolean flag = false;
    StringBuilder mergedStr = new StringBuilder("");
    public List<CajaImpl> cajas = new ArrayList<>(N);
    
    Cajas singleton;
    
    public Cajas() {
    	// Hay que llamar a Geometria para obtener los parametros
    	
    	/*
    	
    	Geometry g = new Geometry();
    	
    	List<Vector> list = g.getPayingPositions();
    	
    	double delta = getDistanceBetweenCajas();
    	
    	*/
    	
    	init();// sin esta linea obtenemos un java.lang.IndexOutOfBoundsException
    }
    
    public Cajas(List<Vector> payingPositions , double distanceBetweenCajas) {
    	/*
    	payingPositions = [L0,R0,L1,R1,L2,R2,...,Ln,Rn];
    	N , la cantidad de cajas , seria payingPositions.length / 2;
    	D = R0 - L0;
    	p // punto inception de la paremetrización de cada caja
    	delta = distanceBetweenCajas;
    	*/
    }
    
    public Cajas(int N,double delta,double D,double H,double d,int max) {
    	this.N = N;
    	this.delta = delta;
    	this.D = D;
    	this.H = H;
    	this.d = d;
    	this.max = max;
    	init();
    }
    
    public void init() {
	    for(int n = 0 ; n < N ; n++) {
	    	Vector pn = new Vector(n*delta,0);
	    	CajaImpl cajan = new CajaImpl(pn,D,H,d,max);
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
//	        		String status = this.status();
//	    	        System.out.println(status);
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
    
   public String status() {
	   StringBuilder str = new StringBuilder();
	   for(int i = 0 ; i < N ; i++) {
		   str.append("Fila " + i + " : ");
		   for(Integer id : cajas.get(i).queue) {
			   str.append(id + " ");
		   }
		   str.append("\n");
	   }
	   return str.toString();
   }
   
	@Override
	public Vector position(int index) {
		Vector p = new Vector(cajas.get(index).position(index).x , cajas.get(index).position(index).y + index*cajas.get(index).d + cajas.get(index).H);
		return p;
	}
	
	@Override
	public boolean hasFreeSpace(int index) {
		return cajas.get(index).queue.size() < N;
	}
	
	@Override
	public void add(int elem , int index , Agent agent) {
		if(cajas.get(index).queue.size() < N) {
			//cajas.get(index).queue.add(elem);
			cajas.get(index).queue.add(agent.id);
		}
	}
	
	@Override
	public int whereToGo() {
		int index = 0;
		int minl = Integer.MAX_VALUE;
		
		for(int i = 0 ; i < N ; i++) {
			int l = cajas.get(i).queue.size();
			//System.out.println(String.format("La caja nro. %d tiene %d elementos",i,l));
			if(minl > l) {
				//System.out.println( minl +  " > " + l);
				minl = l;
				index = i;
			}
		}
		
		return index;
	}

	@Override
	public Vector getPositionOf(int agentid) {
		Vector ret = null;
		for(int i = 0 ; i < N ; i++) {
			int placeInCaja = 0;
			for(Integer id : cajas.get(i).queue) {
				if(id==agentid) {
					//ret = new Vector(cajas.get(i).position(i).x , cajas.get(i).position(i).y + i*cajas.get(i).d + cajas.get(i).H);
					//double x = cajas.get(i).position(i).x;
					//double y = cajas.get(i).position(i).y;
					Vector vec = this.cajas.get(i).position(placeInCaja);
					//ret = new Vector(x,y);
					ret = vec;
					return ret;
				}
				placeInCaja++;
			}
		}
		return null;
	}
	@Override
	public Cajas getInstance() {
		if (singleton == null)
	          singleton=new Cajas();
	      return singleton;
	}
}
