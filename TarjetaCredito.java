package Punto2;

import java.util.ArrayList;

public interface TarjetaCredito {
    double calcularDescuento(ArrayList<Menu> items);

    String getTipo();
}