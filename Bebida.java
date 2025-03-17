package Punto2;

public class Bebida extends Menu {

    public Bebida(String nombre, double precio) {
        super(nombre, precio);
    }

    @Override
    public String getTipo() {
        return "Bebida";
    }
}