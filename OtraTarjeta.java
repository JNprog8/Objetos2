package Punto2;

import java.util.ArrayList;

public class OtraTarjeta implements TarjetaCredito {
    private final String tipo;

    public OtraTarjeta(String tipo) {
        if (tipo == null || tipo.trim().isEmpty()) {
            throw new IllegalArgumentException("El tipo de tarjeta no puede estar vacío.");
        }
        this.tipo = tipo;
    }

    @Override
    public double calcularDescuento(ArrayList<Menu> items) {
        return 0; // Sin descuento
    }

    @Override
    public String getTipo() {
        return tipo;
    }
}