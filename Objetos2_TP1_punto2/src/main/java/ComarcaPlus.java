import java.time.YearMonth;

public class ComarcaPlus extends TarjetaCredito {
    private final float descuento;

    public ComarcaPlus(String numero, String titular, YearMonth expiracion) {
        super(numero, titular, expiracion);
        this.descuento = 0.02F;
    }

    @Override
    public float aplicarDescuento(float totalBebidas, float totalPlatos) {
        float total = totalBebidas + totalPlatos;
        return total - (total * descuento);
    }
}