package ar.edu.itba.sds;

public class App {
    public static void main( String[] args ){
        System.out.println("Hello World!");
        System.out.println("Driver Class Caja Module");
        Position p = new Position(0,0);
        CajaImpl caja = new CajaImpl(p);
        System.out.println(caja);
    }
}
