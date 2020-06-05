package ar.edu.itba.sds.caja;

public class Cajero {
	boolean ocupado = false;
	double T =3; // Entre 3 y 5,5 minutos segun variable exponencial. Calculo de un lamda. Depende de la cantidad de items. // Llamar al servicio que armo el grupo de geometria
	double t = 0;
	
	public void work() {
		ocupado = true;
		t = System.currentTimeMillis();
	}
	public double elapsed() {
		return (System.currentTimeMillis() - t)/1000;
	}
	
	public boolean isDone() {
		return this.elapsed() >= T;
	}
	
	public void rest() {
		ocupado = false;
		t = 0;
	}
	
	@Override
	public String toString() {
		return String.format("%s	%.2f	%.2f",ocupado,T,this.elapsed());
	}
	
}
