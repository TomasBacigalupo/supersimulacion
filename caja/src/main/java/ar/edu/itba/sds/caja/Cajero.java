package ar.edu.itba.sds.caja;

public class Cajero {
	boolean ocupado = false;
	double T = 900; //TIME UNIT ?
	double t = 0;
	
	public void work() {
		ocupado = true;
		t = System.currentTimeMillis();
	}
	
	public double elapsed() {
		return System.currentTimeMillis() - t;
	}
	
	public boolean isDone() {
		return this.elapsed() >= T;
	}
	
	public void rest() {
		ocupado = false;
		t = 0;
	}
	
}
