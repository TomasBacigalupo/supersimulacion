package ar.edu.itba.sds.caja;

import java.util.LinkedList;

import java.util.Queue;
import java.util.concurrent.TimeUnit;

import ar.edu.itba.sds.model.Vector;

public class CajaImpl {
	
	Vector p;
	double D;
	double H;
	Vector L;
	Vector R;
	double d;
	Cajero A = new Cajero();
	Cajero B = new Cajero();
	int counterA = 0;
	int counterB = 0;
	
	Queue<Integer> queue  = new LinkedList <>();
	Queue<Integer> paying = new LinkedList <>();
	
	int x = 0;
	
	StringBuilder ovito = new StringBuilder();
	
	public CajaImpl(Vector p,double D,double H,double d,int max) {
		/*
		p is the inception position
		D is the distance that separates the attention positions
		
		|	|	null	|	|
		|	|	null	|	|
		|	|	id4		|	|
				.
				.
				.
				id3		------------> let x be (x0,y0) then the i-th 0 is (x0,y0 + i*d + H)
				.
				.
				.
		|	|	id2		|	| -> d
		|	|	id1		|	|
		|	|	id0		|	|
		-
		|
		H
		|
		-
		--L--	x	 --R--
		
			   |p|----D---|
		*/
		this.p = p;
		this.D = D;
		this.H = H;
		Vector aux = new Vector(D,0);
		this.L = new Vector(p.x,p.y);
		L.substract(aux);
		this.R = new Vector(p.x,p.y);
		R.add(aux);
		this.d = d;
		A.T = 3;
		B.T = 9;
	}
	
	public Vector position(int index) {
		Vector p = new Vector(this.p.x , this.p.y + index*this.d + this.H);
		return p;
	}
	
	public void atender() {
		if(!A.ocupado) {
			Integer a = queue.poll();
			if(a!=null) {
				A.work();
				paying.add(a);
			};
		}
		if(!B.ocupado) {
			Integer b = queue.poll();
			if(b!=null) {
				B.work();
				paying.add(b);
			};
		}
		if(A.isDone()) {
			A.rest();
			//this.printOvito();//StringBuilder goes full and throws out of heap exception
			Integer p = paying.poll();
			if(p!=null) {
				this.print();
				System.out.println("----------------------------------------------");
				counterA++;
			}
		}
		if(B.isDone()) {
			B.rest();
			//this.printOvito();//StringBuilder goes full and throws out of heap exception
			Integer p = paying.poll();
			if(p!=null) {
				this.print();
				System.out.println("----------------------------------------------");
				counterB++;
			}
		}
	}
	
	public void print() {
		print(this.queue);
		printPaying();
	}
	
	public void printPaying() {
		int counter = 0;
		for(Integer i : paying) {
			System.out.print("x ");
			counter ++;
		}
		System.out.println();
	}
	
	private void print(Queue<Integer> q) {
		System.out.print("[ " + A.ocupado + " ]");
		System.out.print("[ " + B.ocupado + " ]");
		System.out.print("[");
		int counter = 0;
		for(Integer i : q) {
			System.out.print("x ");
			counter ++;
		}
		System.out.println("]");
	}

	public boolean isDone() {
		boolean ret =  queue.isEmpty() && paying.isEmpty() && A.ocupado==false && B.ocupado==false;
		if(ret) {
			this.print();
			System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		}
		return ret;
	}
	
	public void printOvito() {
		ovito.append(this.toOvito());
	}
	
	public String toOvitoBody() {
		StringBuilder str = new StringBuilder("");
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
		return str.toString();
	}
	
	public String toOvito(){
		StringBuilder str = new StringBuilder();
		str.append(queue.size() + 2 + "\n");
		str.append("//\n");
		str.append(this.toOvitoBody());
		return str.toString();
    }

		
}

