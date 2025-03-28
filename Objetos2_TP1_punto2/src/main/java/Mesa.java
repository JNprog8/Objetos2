import java.util.ArrayList;
import java.util.List;

public class Mesa {
    public static final String DESCRIPCION_INVALIDA = "La descripción no puede ser vacía";
    private List<Menu> bebidas;
    private List<Menu> platos;
    private String descripcion;

    public Mesa(String descripcion) {
        if (descripcion.isBlank()) {
            throw new RuntimeException(DESCRIPCION_INVALIDA);
        }
        this.descripcion = descripcion;
        this.bebidas = new ArrayList<>();
        this.platos = new ArrayList<>();
    }

    public void agregarBebida(Menu bebida) {
        bebidas.add(bebida);
    }

    public void agregarPlato(Menu plato) {
        platos.add(plato);
    }

    public float calcularTotal() {
        return calcularTotalBebidas() + calcularTotalPlatos();
    }

    private float calcularTotalPlatos() {
        return platos
                .stream()
                .map(Menu::obtenerPrecio)
                .reduce(0f, Float::sum);
    }

    private float calcularTotalBebidas() {
        return bebidas
                .stream()
                .map(Menu::obtenerPrecio)
                .reduce(0f, Float::sum);
    }

    public float calcularTotalConDescuentoYPropina(TarjetaCredito tarjeta, Propina propina) {
        float totalConDescuento = tarjeta.aplicarDescuento(calcularTotalBebidas(), calcularTotalPlatos());
        return totalConDescuento + (totalConDescuento * propina.obtenerPorcentaje());
    }
}