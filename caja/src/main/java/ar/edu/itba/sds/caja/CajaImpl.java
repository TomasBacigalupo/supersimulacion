package ar.edu.itba.sds.caja;

import java.util.List;


import ar.edu.itba.sds.interfaces.Caja;
import ar.edu.itba.sds.model.Position;

public class CajaImpl implements Caja {
	
	Position p;
	double D;
	double H;
	Position L;
	Position R;
	double d;
	
	public CajaImpl(Position p,double D,double H,double d) {
		/*
		p is the inception position
		D is the distance that separates the attention positions
		
		|	|	0	|	|
		|	|	0	|	|
		|	|	0	|	|
				.
				.
				.
				0 ------------> let x be (x0,y0) then the i-th 0 is (x0,y0 + i*d + H)
				.
				.
				.
		|	|	0	|	| -> d
		|	|	0	|	|
		|	|	0	|	|
		-
		|
		H
		|
		-
		--L--	x	 --R--
		
			   |p|-----D--|
		*/
		this.p = p;
		this.D = D;
		this.H = H;
		Position aux = new Position(D,0);
		this.L = new Position(p.x,p.y);
		L.substract(aux);
		this.R = new Position(p.x,p.y);
		R.add(aux);
		this.d = d;
	}
	
	@Override
	public Position position(int index) {
		Position p = new Position(this.p.x , this.p.y + index*this.d + this.H);
		return p;
	}
	
	@Override
	public boolean hasFreeSpace(int index) {
		return false;
	}
	
	@Override
	public List<Integer> status() {
		return null;
	}
	
	@Override
	public String toString() {
		return String.format("p = %s		L = %s	R = %s	\n",p.toString(),L.toString(),R.toString());
	}

}
