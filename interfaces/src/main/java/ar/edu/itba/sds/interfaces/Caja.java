package ar.edu.itba.sds.interfaces;

import java.util.List;


import ar.edu.itba.sds.model.Position;

public interface Caja {
	public int whereToGo();
	public Position position(int index);
	public boolean hasFreeSpace(int index);
	public List<Integer> status();
	public void add(int index/* , int elems , Agent agent*/);
}
