package Punto2;

import java.util.ArrayList;

public class ComarcaPlus implements TarjetaCredito {

    @Override
    public double calcularDescuento(ArrayList<Menu> items) {
        return items.stream()
                .mapToDouble(Menu::getPrecio)
                .sum() * 0.02;
    }

    @Override
    public String getTipo() {
        return "Comarca Plus";
    }
}