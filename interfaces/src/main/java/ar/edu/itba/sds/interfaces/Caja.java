package ar.edu.itba.sds.interfaces;

import java.util.List;


import ar.edu.itba.sds.model.Position;

public interface Caja {
	Position position(int index);
	boolean hasFreeSpace(int index);
	List<Integer> status();
}
