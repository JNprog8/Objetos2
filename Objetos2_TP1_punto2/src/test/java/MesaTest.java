import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.YearMonth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MesaTest {

    private Mesa mesa;
    private Menu producto1;
    private Menu producto2;

    @BeforeEach
    public void setup() {
        mesa = new Mesa("Mesa 1");
        producto1 = new Menu("Coca Cola", 6500);
        producto2 = new Menu("Pizza", 10000);

        mesa.agregarBebida(producto1);
        mesa.agregarPlato(producto2);
    }

    @Test
    public void montoTotal() {
        assertEquals(16500f, mesa.calcularTotal());
    }

    @Test
    public void montoTotalConVisa() {
        var tarjeta = new Visa("1234567890123456", "Juan Perez", YearMonth.now().plusMonths(10));
        float actual = mesa.calcularTotalConDescuentoYPropina(tarjeta, Propina.BAJO);

        // Descuento Visa: 3% en bebidas (6500 * 0.03 = 195)
        // Total con descuento: 10000 + (6500 - 195) = 16305
        // Total con propina baja (2%): 16305 + (16305 * 0.02) = 16631.1
        assertEquals(16631.1f, actual);
    }

    @Test
    public void montoTotalConMastercard() {
        var tarjeta = new Mastercard("1234567890123456", "Juan Perez", YearMonth.now().plusMonths(10));

        // Descuento Mastercard: 2% en platos (10000 * 0.02 = 200)
        // Total con descuento: 6500 + (10000 - 200) = 16300
        // Total con propina alta (5%): 16300 + (16300 * 0.05) = 17115
        assertEquals(17115f, mesa.calcularTotalConDescuentoYPropina(tarjeta, Propina.ALTO));
    }

    @Test
    public void montoTotalConComarcaPlus() {
        var tarjeta = new ComarcaPlus("1234567890123456", "Juan Perez", YearMonth.now().plusMonths(10));

        // Descuento ComarcaPlus: 2% en total (16500 * 0.02 = 330)
        // Total con descuento: 16500 - 330 = 16170
        // Total con propina alta (5%): 16170 + (16170 * 0.05) = 16978.5
        assertEquals(16978.5f, mesa.calcularTotalConDescuentoYPropina(tarjeta, Propina.ALTO), 0.1f);
    }

    @Test
    public void montoTotalConTarjetaViedma() {
        // Uso OtraTarjeta para representar la tarjeta Viedma (sin descuento)
        var tarjeta = new OtraTarjeta("1234567890123456", "Juan Perez", YearMonth.now().plusMonths(10));

        // Sin descuento: 16500
        // Total con propina media (3%): 16500 + (16500 * 0.03) = 16995
        assertEquals(16995f, mesa.calcularTotalConDescuentoYPropina(tarjeta, Propina.MEDIO));
    }

}