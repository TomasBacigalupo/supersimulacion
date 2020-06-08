package ar.edu.itba.sds.model;

import java.util.Vector;

public class VectorFila {
	
	public double x;
	public double y;
	
	public VectorFila(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public void add(VectorFila other) {
		this.x += other.x;
		this.y += other.y;
	}
	
	public void substract(VectorFila other){
		this.x -= other.x;
		this.y -= other.y;
	}
	
	public void escalarProduct(double x) {
		this.x = this.x*x;
		this.y = this.y*x;
	}
	
	public VectorFila A(Vector<Double> v) {
		double x = (double) v.get(0);
		double y = (double) v.get(1);
		VectorFila vf = new VectorFila(x,y);
		return vf;
	}
	
	public Vector<Double> B(VectorFila v){
		Vector<Double> ret = new Vector<Double>();
		double x = v.x;
		double y = v.y;
		ret.add(x);
		ret.add(y);
		return ret;
	}
	
	@Override
	public String toString() {
		return String.format("%.2f %.2f",x,y);
	}
	
}
