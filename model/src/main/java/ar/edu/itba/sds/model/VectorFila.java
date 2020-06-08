package ar.edu.itba.sds.model;

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
	
	@Override
	public String toString() {
		return String.format("%.2f %.2f",x,y);
	}
	
}
