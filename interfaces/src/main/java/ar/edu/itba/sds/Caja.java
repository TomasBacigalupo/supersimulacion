package ar.edu.itba.sds;

import java.util.List;

public interface Caja {
	//Position position(int index);
	boolean hasFreeSpace(int index);
	List<Integer> status();
}
