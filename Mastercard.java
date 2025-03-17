package Punto2;

import java.util.ArrayList;

public class Mastercard implements TarjetaCredito {

    @Override
    public double calcularDescuento(ArrayList<Menu> items) {
        return items.stream()
                .filter(i -> i.getTipo().equals("Comida"))
                .mapToDouble(Menu::getPrecio)
                .sum() * 0.02;
    }

    @Override
    public String getTipo() {
        return "Mastercard";
    }
}