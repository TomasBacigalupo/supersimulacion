package ar.edu.itba.sds.interfaces;

import ar.edu.itba.sds.model.Agent;
import ar.edu.itba.sds.model.Vector;

public interface Caja {
	public int whereToGo();
	public Vector position(int index);
	public boolean hasFreeSpace(int index);
	public void add(int index , int elems  , Agent agent);
	public Vector getPositionOf(int agentid);
}
