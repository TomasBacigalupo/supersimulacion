package ar.edu.itba.sds;

public class Position {
	
	double x;
	double y;
	
	public Position(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public String toString() {
		return String.format("%.2f	%.2f",x,y);
	}
	
}
