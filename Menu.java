package Punto2;

public abstract class Menu {
    private final String nombre;
    private final double precio;

    public Menu(String nombre, double precio) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        if (precio <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor a cero.");
        }
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public abstract String getTipo();

    @Override
    public String toString() {
        return getNombre() + " - $" + getPrecio();
    }
}