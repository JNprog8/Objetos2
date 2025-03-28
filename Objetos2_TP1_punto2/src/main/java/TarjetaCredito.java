import java.time.YearMonth;

public abstract class TarjetaCredito {
    public static final String NUMERO_VACIO = "El número no puede ser vacío";
    public static final String NUMERO_TARJETA_NO_VALIDO = "El número debe tener 16 dígitos";
    public static final String TITULAR_VACIO = "El titular no puede ser vacío";
    public static final String FECHA_EXPIRACION_DEBE_SER_FUTURA = "La tarjeta está vencida";
    private String numero;
    private String titular;
    private YearMonth expiracion;

    public TarjetaCredito(String numero, String titular, YearMonth expiracion) {
        validarNumero(numero);
        validarTitular(titular);
        validarExpiracion(expiracion);
        this.numero = numero;
        this.titular = titular;
        this.expiracion = expiracion;
    }

    private void validarExpiracion(YearMonth expiracion) {
        if (expiracion.isBefore(YearMonth.now())) {
            throw new RuntimeException(FECHA_EXPIRACION_DEBE_SER_FUTURA);
        }
    }

    private void validarTitular(String titular) {
        if (titular.isBlank()) {
            throw new RuntimeException(TITULAR_VACIO);
        }
    }

    private void validarNumero(String numero) {
        if (numero.isBlank()) {
            throw new RuntimeException(NUMERO_VACIO);
        }
        if (numero.length() != 16) {
            throw new RuntimeException(NUMERO_TARJETA_NO_VALIDO);
        }
    }

    public abstract float aplicarDescuento(float totalBebidas, float totalPlatos);
}