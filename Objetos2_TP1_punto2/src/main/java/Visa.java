import java.time.YearMonth;

public class Visa extends TarjetaCredito {
    private final float descuento;

    public Visa(String numero, String titular, YearMonth expiracion) {
        super(numero, titular, expiracion);
        this.descuento = 0.03F;
    }

    @Override
    public float aplicarDescuento(float totalBebidas, float totalPlatos) {
        return totalPlatos + (totalBebidas - (totalBebidas * descuento));
    }
}