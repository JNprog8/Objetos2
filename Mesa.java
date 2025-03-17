package Punto2;

public class Mesa {
    private final int numeroMesa;
    private final int capacidad;
    private Pedido pedido;
    private boolean reservada;

    public Mesa(int numeroMesa, int capacidad) {
        if (numeroMesa <= 0 || capacidad <= 0) {
            throw new IllegalArgumentException("Número de mesa o capacidad inválidos.");
        }
        this.numeroMesa = numeroMesa;
        this.capacidad = capacidad;
        this.reservada = false;
    }

    public int obtenerNumeroMesa() {
        return numeroMesa;
    }

    public int obtenerCapacidad() {
        return capacidad;
    }

    public boolean estaOcupada() {
        return reservada;
    }

    public void ocupar() {
        if (reservada) {
            throw new IllegalStateException("La mesa ya está ocupada.");
        }
        reservada = true;
    }

    public void liberar() {
        if (!reservada) {
            throw new IllegalStateException("La mesa no está ocupada.");
        }
        if (pedido != null && !pedido.estaConfirmado()) {
            throw new IllegalStateException("Hay un pedido sin confirmar.");
        }
        reservada = false;
        pedido = null;
    }

    public void tomarPedido() {
        if (!reservada) {
            throw new IllegalStateException("La mesa no está ocupada.");
        }
        if (pedido != null && pedido.estaConfirmado()) {
            throw new IllegalStateException("Ya hay un pedido confirmado.");
        }
        pedido = new Pedido();
    }

    public void agregarAlPedido(Menu item) {
        if (!reservada) {
            throw new IllegalStateException("La mesa no está ocupada.");
        }
        if (pedido == null) {
            throw new IllegalStateException("No se ha iniciado un pedido.");
        }
        pedido.agregarItem(item);
    }

    public void confirmarPedido(TarjetaCredito tarjeta, Propina propina) {
        if (!reservada) {
            throw new IllegalStateException("La mesa no está ocupada.");
        }
        if (pedido == null) {
            throw new IllegalStateException("No hay pedido.");
        }
        pedido.confirmar(tarjeta, propina);
    }

    public Pedido obtenerPedido() {
        if (pedido == null || !pedido.estaConfirmado()) {
            throw new IllegalStateException("No hay pedido confirmado.");
        }
        return pedido;
    }
}