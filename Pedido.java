package Punto2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Pedido {
    private final ArrayList<Menu> productos;
    private boolean estaConfirmado;
    private TarjetaCredito tarjeta;
    private Propina propina;

    public Pedido() {
        this.productos = new ArrayList<>();
    }

    public void agregarItem(Menu item) {
        if (estaConfirmado) {
            throw new IllegalStateException("El pedido ya está confirmado y no puede modificarse.");
        }
        productos.add(item);
    }

    public void confirmar(TarjetaCredito tarjeta, Propina propina) {
        if (estaConfirmado) {
            throw new IllegalStateException("El pedido ya está confirmado.");
        }
        if (productos.isEmpty()) {
            throw new IllegalStateException("No se puede confirmar un pedido vacío.");
        }
        this.estaConfirmado = true;
        this.tarjeta = tarjeta;
        this.propina = propina;
    }

    public boolean estaConfirmado() {
        return estaConfirmado;
    }

    public List<Menu> obtenerProductos() {
        return new ArrayList<>(productos); // Devuelve una copia para no modificar el original
    }

    public TarjetaCredito obtenerTarjeta() {
        return tarjeta;
    }

    public Propina obtenerPropina() {
        return propina;
    }

    public String mostrarProductos() {
        if (productos.isEmpty()) {
            return "No hay productos en el pedido.";
        }

        StringBuilder sb = new StringBuilder("Productos en el pedido:\n");

        // Agrupar por tipo (Bebida/Comida)
        String bebidas = productos.stream()
                .filter(i -> i.getTipo().equals("Bebida"))
                .map(i -> "- " + i.getNombre() + ": $" + i.getPrecio())
                .collect(Collectors.joining("\n"));

        String comidas = productos.stream()
                .filter(i -> i.getTipo().equals("Comida"))
                .map(i -> "- " + i.getNombre() + ": $" + i.getPrecio())
                .collect(Collectors.joining("\n"));

        if (!bebidas.isEmpty()) {
            sb.append("Bebidas:\n").append(bebidas).append("\n");
        }

        if (!comidas.isEmpty()) {
            sb.append("Comidas:\n").append(comidas).append("\n");
        }

        return sb.toString();
    }

    public double calcularCostoTotal() {
        if (!estaConfirmado) {
            throw new IllegalStateException("El pedido no está confirmado.");
        }

        double subtotal = productos.stream().mapToDouble(Menu::getPrecio).sum();
        double descuento = tarjeta.calcularDescuento(productos);
        double montoConDescuento = subtotal - descuento;
        double montoPropina = montoConDescuento * (propina.getValor() / 100.0);

        return montoConDescuento + montoPropina;
    }

    public String generarTicket() {
        if (!estaConfirmado) {
            throw new IllegalStateException("El pedido no está confirmado.");
        }

        double subtotal = productos.stream().mapToDouble(Menu::getPrecio).sum();
        double descuento = tarjeta.calcularDescuento(productos);
        double montoConDescuento = subtotal - descuento;
        double montoPropina = montoConDescuento * (propina.getValor() / 100.0);
        double total = montoConDescuento + montoPropina;

        StringBuilder ticket = new StringBuilder();
        ticket.append("=== TICKET ===\n");
        ticket.append(mostrarProductos());
        ticket.append("\nSubtotal: $").append(String.format("%.2f", subtotal));
        ticket.append("\nDescuento (").append(tarjeta.getTipo()).append("): $").append(String.format("%.2f", descuento));
        ticket.append("\nSubtotal con descuento: $").append(String.format("%.2f", montoConDescuento));
        ticket.append("\nPropina (").append(propina.getValor()).append("%): $").append(String.format("%.2f", montoPropina));
        ticket.append("\nTOTAL: $").append(String.format("%.2f", total));
        ticket.append("\n=============");

        return ticket.toString();
    }
}