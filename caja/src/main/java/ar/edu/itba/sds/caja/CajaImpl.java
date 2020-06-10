package ar.edu.itba.sds.caja;

import java.io.FileWriter;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import ar.edu.itba.sds.interfaces.Caja;
import ar.edu.itba.sds.model.Agent;
import ar.edu.itba.sds.model.VectorFila;

public class CajaImpl implements Caja {
	private int N = 8;// Cantidad de cajas
    private double delta = 20; // Separacion entre el punto p que parametriza cada caja
    private double p0x = 0;
    private double p0y = 0;
    private double D = 7.5;// Distancia de separación entre cajas
	private double H = 5;// Distancia social interno de la caja
	private double d = 2;// Distancia social interno de la caja
	private int max = 8;// Máxima capacidad de cada fila interno de la caja
	
    private int i = 0;
    boolean flag = false;
    StringBuilder mergedStr = new StringBuilder("");
    public List<CajaAux> cajas = new ArrayList<>(N);
    
    static CajaImpl singleton;
    
    public TimesService ts = new TimesService();
    public double timeAcum = 0;
    
    public int atendidos = 0;
    
    public CajaImpl() {
    	/*
    	
    	Geometry g = Geometry.getInstance();
    	
    	List<Vector<Double> list = g.getPayingPositions();
    	
    	double delta = g.getDistanceBetweenCajas();
   	    	
    	CajaImpl ret = new CajaImpl(list,delta);
    	
    	/*
    	
    	payingPositions = [p0 , p1 , p2 , ... , pn];
    	
    	//	pi = {pix , piy}
    	
    	payingPositions = [{p0x,p0y},{p1x,p1y},{p2x,p2y},...,{pnx,pny}];
    	
    	delta = distanceBetweenCajas;
    	*/
    	
//    	this.N = payingPositions.size();
//    	this.p0x = payingPositions.get(0).get(0);
//    	this.p0y = payingPositions.get(0).get(1);
//    	this.delta = distanceBetweenCajas;
    	
    	init();
    }
    
    public CajaImpl(List<Vector<Double>> payingPositions , double distanceBetweenCajas) {
    	/*
    	
    	payingPositions = [p0 , p1 , p2 , ... , pn];
    	
    	//	pi = {pix , piy}
    	
    	payingPositions = [{p0x,p0y},{p1x,p1y},{p2x,p2y},...,{pnx,pny}];
    	
    	delta = distanceBetweenCajas;
    	*/
    	
    	this.N = payingPositions.size();
    	this.p0x = payingPositions.get(0).get(0);
    	this.p0y = payingPositions.get(0).get(1);
    	this.delta = distanceBetweenCajas;
    	init();
    }
    
    public CajaImpl(int N,double delta,double D,double H,double d,int max) {
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
	    	VectorFila pn = new VectorFila(p0x + n*delta , p0y);
	    	CajaAux cajan = new CajaAux(pn,D,H,d,max);
	    	cajas.add(cajan);
	    }
    }
    
    public void run() {
        for(int m = 0 ; m < N ; m++) {
	        while(!cajas.get(m).isDone()) {
	        	i++;
	        	if(i%10000000==0) {
	        		flag = true;
	        	}
	        	int size = 0;
	        	StringBuilder str = new StringBuilder();
	        	for(int n = 0 ; n < N ; n++) {			        	
		        	if(flag){
		        		cajas.get(n).printOvito();
		        		size = size + cajas.get(n).queue.size() + 2;
		        		str.append(cajas.get(n).toOvitoBody());
		        		//cajas.get(n).print();
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
        int atendidosEstaVuelta = 0;
        for(int n = 0 ; n < N ; n++) {
	        System.out.println(cajas.get(n).counterA + "	" + cajas.get(n).counterB);
	        atendidosEstaVuelta += cajas.get(n).counterA + cajas.get(n).counterB;
	        write("outputMerged.txt",mergedStr.toString());
        }
        atendidos=atendidosEstaVuelta;
        System.out.println("Atendidos: " + atendidos + "---------------------------------||||||||||||||||||||||||||||||");
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
	public VectorFila position(int index) {
		int n = cajas.get(index).queue.size();
		VectorFila p = cajas.get(index).position(n);
		return p;
	}
	
	@Override
	public boolean hasFreeSpace(int index) {
		return cajas.get(index).queue.size() < N;
	}
	
	@Override
	public void add(int elems , int index , Agent agent) {
		if(cajas.get(index).queue.size() < N) {
			//cajas.get(index).queue.add(agent.id);
			double t = ts.getCashierWaitingTime(elems);
			timeAcum += t;
			cajas.get(index).add(agent , t);
		}
	}
	
	@Override
	public int whereToGo() {
		int index = 0;
		int minl = Integer.MAX_VALUE;
		for(int i = 0 ; i < N ; i++) {
			int l = cajas.get(i).queue.size();
			if(minl > l) {
				minl = l;
				index = i;
			}
		}
		return index;
	}

	@Override
	public VectorFila getPositionOf(int agentid) {
		VectorFila ret = null;
		for(int i = 0 ; i < N ; i++) {
			int placeInCaja = 0;
			for(Integer id : cajas.get(i).queue) {
				if(id==agentid) {
					VectorFila vec = this.cajas.get(i).position(placeInCaja);
					ret = vec;
					return ret;
				}
				placeInCaja++;
			}
		}
		return null;
	}

	public static CajaImpl getInstance() {
		if (singleton == null)
	          singleton=new CajaImpl();
	      return singleton;
	}

	@Override
	public int atendidos() {
		return atendidos;
	}

	@Override
	public Vector<Double> positionAux(int index) {
		VectorFila aux = this.position(index);
		Vector<Double> ret = aux.B(aux);
		return ret;
	}

	@Override
	public Vector<Double> getPositionOfAux(int agentid) {
		VectorFila aux = this.getPositionOf(agentid);
		Vector<Double> ret = aux.B(aux);
		return ret;
	}

	@Override
	public double getSectorPosition() {
		return p0y + 6;
	}
}
