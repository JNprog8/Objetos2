package Punto2;

import java.util.ArrayList;

public class Visa implements TarjetaCredito {

    @Override
    public double calcularDescuento(ArrayList<Menu> items) {
        return items.stream()
                .filter(i -> i.getTipo().equals("Bebida"))
                .mapToDouble(Menu::getPrecio)
                .sum() * 0.03;
    }

    @Override
    public String getTipo() {
        return "Visa";
    }
}