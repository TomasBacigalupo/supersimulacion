package ar.edu.itba.sds;

public interface Caja {
	Position position(int index);
	boolean hasFreeSpace(int index);
	List<Integer> status();
}
