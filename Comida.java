package Punto2;

public class Comida extends Menu {
    public Comida(String nombre, double precio) {
        super(nombre, precio);
    }

    @Override
    public String getTipo() {
        return "Comida";
    }
}