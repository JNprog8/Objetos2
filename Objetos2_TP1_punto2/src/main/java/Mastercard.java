import java.time.YearMonth;

public class Mastercard extends TarjetaCredito {
    private final float descuento;

    public Mastercard(String numero, String titular, YearMonth expiracion) {
        super(numero, titular, expiracion);
        this.descuento = 0.02F;
    }

    @Override
    public float aplicarDescuento(float totalBebidas, float totalPlatos) {
        return totalBebidas + (totalPlatos - (totalPlatos * descuento));
    }
}