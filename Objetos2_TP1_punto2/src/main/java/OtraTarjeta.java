import java.time.YearMonth;

public class OtraTarjeta extends TarjetaCredito {

    public OtraTarjeta(String numero, String titular, YearMonth expiracion) {
        super(numero, titular, expiracion);
    }

    @Override
    public float aplicarDescuento(float totalBebidas, float totalPlatos) {
        return totalBebidas + totalPlatos;
    }
}