package ar.edu.itba.sds.model;

public class Position {
	
	public double x;
	public double y;
	
	public Position(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public void add(Position other) {
		this.x += other.x;
		this.y += other.y;
	}
	
	public void substract(Position other){
		this.x -= other.x;
		this.y -= other.y;
	}
	
	public void escalarProduct(double x) {
		this.x = this.x*x;
		this.y = this.y*x;
	}
	
	@Override
	public String toString() {
		return String.format("(%.2f,%.2f)",x,y);
	}
	
}
