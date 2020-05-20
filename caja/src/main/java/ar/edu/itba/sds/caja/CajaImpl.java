package ar.edu.itba.sds.caja;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import ar.edu.itba.sds.interfaces.Caja;
import ar.edu.itba.sds.model.Position;

public class CajaImpl implements Caja {
	
	Position p;
	double D;
	double H;
	Position L;
	Position R;
	double d;
	Cajero A = new Cajero();
	Cajero B = new Cajero();
	int max;
	Queue<Integer> queue = new LinkedList <>();//waiting
	//paying
	int x = 0;
	
	public CajaImpl(Position p,double D,double H,double d,int max) {
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
		this.max = max;
	}
	
	@Override
	public Position position(int index) {
		Position p = new Position(this.p.x , this.p.y + index*this.d + this.H);
		return p;
	}
	
	@Override
	public boolean hasFreeSpace() {
		return queue.size() < max;
	}
	
	@Override
	public List<Integer> status() {
		return null;
	}
	
	@Override
	public String toString() {
		return String.format("p = %s		L = %s	R = %s	\n",p.toString(),L.toString(),R.toString());
	}
	
	@Override
	public void add(int elem) {
		queue.add(elem);
	}

	public void atender() {
		//print(queue);
		if(!A.ocupado) {
			A.work();
			//System.out.println("A work");
		}
		if(!B.ocupado) {
			B.work();
			//System.out.println("B work");
		}
		if(A.isDone()) {
			A.rest();
			x = queue.poll();
			//System.out.println("A rest");
			//System.out.println(x + " goes home");
			//System.out.println(A);
		}
		if(B.isDone()) {
			B.rest();
			x = queue.poll();
			//System.out.println("B rest");
			//System.out.println(x + " goes home");
			//System.out.println(B);
		}
		if(B.isDone() || A.isDone()) {
			print(queue);
		}
	}

	private void print(Queue<Integer> q) {
		System.out.print("[");
		for(Integer i : q) {
			System.out.print(i + " ");
		}
		System.out.println("]");
	}

	public boolean isDone() {
		return queue.isEmpty();
	}
	
	
	
}

