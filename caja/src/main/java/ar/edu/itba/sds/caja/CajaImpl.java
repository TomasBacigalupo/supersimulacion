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
	int counterA = 0;
	int counterB = 0;
	int max;
	
	Queue<Integer> queue = new LinkedList <>();
	
	int x = 0;
	
	StringBuilder ovito = new StringBuilder();
	
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
		A.T = 3;
		B.T = 9;
	}
	
	@Override
	public Position position(int index) {
		Position p = new Position(this.p.x , this.p.y + index*this.d + this.H);
		return p;
	}
	
	@Override
	public boolean hasFreeSpace(int index) {
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
		if(queue.size() < max)
			queue.add(elem);
	}

	public void atender() {
		if(!A.ocupado) {
			A.work();
			queue.poll();
		}
		if(!B.ocupado) {
			B.work();
			queue.poll();
		}
		if(A.isDone()) {
			A.rest();
			this.printOvito();
			counterA++;
		}
		if(B.isDone()) {
			B.rest();
			this.printOvito();
			counterB++;
		}
	}
	
	public void print() {
		print(this.queue);
	}
	
	private void print(Queue<Integer> q) {
		System.out.print("[ " + A.ocupado + " ]");
		System.out.print("[ " + B.ocupado + " ]");
		System.out.print("[");
		int counter = 0;
		for(Integer i : q) {
			System.out.print(this.position(counter) + " ");
			counter ++;
		}
		System.out.println("]");
	}

	public boolean isDone() {
		return queue.isEmpty();
	}
	
	public void printOvito() {
		ovito.append(this.toOvito());
	}
	
	public String toOvito(){
		StringBuilder str = new StringBuilder();
		str.append(queue.size() + 2 + "\n");
		str.append("//\n");
		if(!A.ocupado) {
			str.append("-2 " + L + " 0.5 1 0 0 \n");
		}else {
			str.append("-2 " + L + " 0.5 0 0 1 \n");
		}
		if(!B.ocupado) {

			str.append("-1 " + R + " 0.5 1 0 0 \n");
		}else {

			str.append("-1 " + R + " 0.5 0 0 1 \n");
		}
		
		int counter = 0;
		for(Integer i : this.queue) {
			str.append(counter + " ");
			str.append(this.position(counter));
			str.append(" 0.5");
			str.append(" 0 0 1");
			str.append("\n");
			counter ++;
		}
		//str.append(App.poligons());
		return str.toString();
    }

	@Override
	public int whereToGo() {
		// TODO Auto-generated method stub
		return 0;
	}
	/*
	@Override
	public boolean hasFreeSpace(int index) {
		// TODO Auto-generated method stub
		return false;
	}
	*/
	
}

