package ar.edu.itba.sds;

import java.util.List;

public class CajaImpl implements Caja {
	
	Position p;
	
	public CajaImpl(Position p) {
		this.p = p;
	}
	
	public Position position(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean hasFreeSpace(int index) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<Integer> status() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String toString() {
		return p.toString();
	}

}
