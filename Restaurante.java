package Punto2;

import java.util.ArrayList;
import java.util.List;

public class Restaurante {
    private final List<Mesa> mesas;
    private final List<Menu> menu;

    public Restaurante() {
        this.mesas = new ArrayList<>();
        this.menu = new ArrayList<>();
    }

    public void agregarMesa(Mesa mesa) {
        // Verificar que no exista una mesa con el mismo número
        if (mesas.stream().anyMatch(m -> m.obtenerNumeroMesa() == mesa.obtenerNumeroMesa())) {
            throw new IllegalArgumentException("Ya existe una mesa con el número " + mesa.obtenerNumeroMesa());
        }
        mesas.add(mesa);
    }

    public Pedido obtenerPedidoMesa(int numeroMesa) {
        return obtenerMesa(numeroMesa).obtenerPedido();
    }

    public Mesa obtenerMesa(int numeroMesa) {
        return mesas.stream()
                .filter(m -> m.obtenerNumeroMesa() == numeroMesa)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No existe la mesa " + numeroMesa));
    }

    public void agregarMenu(Menu item) {
        menu.add(item);
    }
    
    public List<Menu> obtenerMenu() {
        return new ArrayList<>(menu); // Muestra la carta al comesal
    }
}