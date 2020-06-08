package ar.edu.itba.sds.interfaces;

import ar.edu.itba.sds.model.Agent;
import ar.edu.itba.sds.model.VectorFila;

public interface Caja {
	public int whereToGo();
	public VectorFila position(int index);
	public boolean hasFreeSpace(int index);
	public void add(int index , int elems  , Agent agent);
	public VectorFila getPositionOf(int agentid);
	public int atendidos();
}
